package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    //Image Carousel Array
    CarouselView carouselView;
    public int[] carouselImageList = {
            R.drawable.begonia1,
            R.drawable.variegated2,
            R.drawable.magnolia3,
            R.drawable.moss4};

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DiaryLayoutAdapter adapter;

    TextView text;
    DiaryDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //dummy data
        Diary entry1 = new Diary();
        entry1.setTitle("Dexterity");
        entry1.setQuantity(25);
        entry1.setDescription("finger stretches");
        entry1.setTimestamp("1015");
        database.diaryDao().add(entry1);

        Diary entry2 = new Diary();
        entry2.setTitle("Strength");
        entry2.setQuantity(5);
        entry2.setDescription("removing pickle jar lids");
        entry2.setTimestamp("1030");
        database.diaryDao().add(entry2);

        Diary entry3 = new Diary();
        entry3.setTitle("Endurance");
        entry3.setQuantity(50);
        entry3.setDescription("typing exercises");
        entry3.setTimestamp("1045");
        database.diaryDao().add(entry3);

        //database
        Context context = this.getApplicationContext();
        database = PreferencesHelper.loadDatabase(context);

        database = Room.databaseBuilder(getApplicationContext(), DiaryDatabase.class, "diary-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        List<Diary> diaries = database.diaryDao().getAll();

        //recycler view for display of diary entries
        RecyclerView recyclerView = findViewById(R.id.diary_entry);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DiaryLayoutAdapter(diaries);
        recyclerView.setAdapter(adapter);


        //Image Carousel View
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(carouselImageList.length);
        carouselView.setImageListener(imageListener);
    }

    //Carousel Image Listener
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(carouselImageList[position]);
        }
    };

    public void onHomeButtonClick(View button) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSubmitClick(View view) {
    }

    //talking to Spring backend with Retrofit
    public void onRetrofitClick(View view) {
        String URL = "ADD HEROKU URL";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiaryService service = retrofit.create(DiaryService.class);

        service.getDiaryResults().enqueue(new Callback<List<Diary>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Diary>> call, Response<List<Diary>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    text.setText(response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<List<Diary>> call, Throwable throwable) {
                Log.e("Error Fetching Entries", throwable.toString());
            }
        });
    }
}

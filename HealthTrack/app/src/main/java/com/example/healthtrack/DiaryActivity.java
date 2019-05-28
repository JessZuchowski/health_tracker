package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

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

    DiaryDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        database = Room.databaseBuilder(getApplicationContext(), DiaryDatabase.class, "diary-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        List<Diary> diaries = database.diaryDao().getAll();

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



//
//        ArrayList<Diary> diaries = new ArrayList<>();
//        diaries.add(new Diary("Dexterity", 20, "stretches", "1700"));
//
//        recyclerView = findViewById(R.id.recycler_main);

    public void onHomeButtonClick(View button) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSubmitClick(View view) {
    }
}

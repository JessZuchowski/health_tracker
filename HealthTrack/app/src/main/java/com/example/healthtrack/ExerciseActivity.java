package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ExerciseActivity extends AppCompatActivity {

    //username
    private TextView text;

    private String name;

    //click counter
    public int clickCounter = 0;
    public Button button;
    public TextView text_counter;

    //Image Carousel Array
    CarouselView carouselView;
    public int[] carouselImageList = {
            R.drawable.begonia1,
            R.drawable.variegated2,
            R.drawable.magnolia3,
            R.drawable.moss4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //username
        text = findViewById(R.id.text_name1);

        String filename = getString(R.string.preferences_file);
        SharedPreferences preferences = getSharedPreferences(filename, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.apply();

        //Image Carousel View
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(carouselImageList.length);
        carouselView.setImageListener(imageListener);

        //click counter
        text_counter = findViewById(R.id.text_counter);
        button = findViewById(R.id.count_button);
        text_counter.setText("0");

        //click counter
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCounter = clickCounter + 1;
                text_counter.setText(String.valueOf(clickCounter));
            }
        });

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
}

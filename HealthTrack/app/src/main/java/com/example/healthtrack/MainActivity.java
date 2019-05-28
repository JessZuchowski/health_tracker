package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

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

    public void onExerciseButtonClick(View exercise_button) {
        Intent intent1 = new Intent(this, ExerciseActivity.class);
        startActivity(intent1);
    }

    public void onDiaryButtonClick(View diary_button) {
        Intent intent2 = new Intent(this, DiaryActivity.class);
        startActivity(intent2);
    }

}

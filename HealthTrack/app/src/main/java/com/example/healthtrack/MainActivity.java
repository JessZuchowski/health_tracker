package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {

    //username
    TextView text;
    EditText edit;
    String name;

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

        //username
        text = findViewById(R.id.text_name);
        edit = findViewById(R.id.edit_name);

        SharedPreferences preferences = PreferencesHelper.getFilePreferences(this);
        


        updateText();

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

    //username editor
    public void onNameClick(View view) {
        SharedPreferences preferences = PreferencesHelper.getFilePreferences(this);

        SharedPreferences.Editor editor = preferences.edit();
        editor.apply();
        updateText();
    }

    private void updateText() {
        text.setText(this.name);
    }

    public void onExerciseButtonClick(View exercise_button) {
        Intent intent1 = new Intent(this, ExerciseActivity.class);
        startActivity(intent1);
    }

    public void onDiaryButtonClick(View diary_button) {
        Intent intent2 = new Intent(this, DiaryActivity.class);
        startActivity(intent2);
    }

}

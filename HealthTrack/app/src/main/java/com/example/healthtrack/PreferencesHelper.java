package com.example.healthtrack;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

public class PreferencesHelper {
    public static SharedPreferences getFilePreferences(Activity activity) {
        String filename = activity.getString(R.string.preferences_file);
        SharedPreferences preferences = activity.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences;
    }

    public static DiaryDatabase loadDatabase(Context context) {
        return Room.databaseBuilder(context, DiaryDatabase.class, "diary-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}

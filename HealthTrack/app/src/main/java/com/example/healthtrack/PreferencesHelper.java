package com.example.healthtrack;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    public static SharedPreferences getFilePreferences(Activity activity) {
        String filename = activity.getString(R.string.preferences_file);
        SharedPreferences preferences = activity.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences;
    }
}

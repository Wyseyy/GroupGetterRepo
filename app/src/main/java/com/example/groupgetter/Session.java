package com.example.groupgetter;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    public static void logoutUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

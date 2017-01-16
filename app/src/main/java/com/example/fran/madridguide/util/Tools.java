package com.example.fran.madridguide.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fran.madridguide.R;

/**
 * Created by fran on 16/1/17.
 */
public class Tools {


    public static void shopDataDownloaded(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("DOWNLOADED", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getString(R.string.SHARED_PREFERENCES_SHOPS_KEY), true);
        editor.commit();
    }
    public static void activityDataDownloaded(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("DOWNLOADED", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getString(R.string.SHARED_PREFERENCES_ACTIVITIES_KEY), true);
        editor.commit();
    }

    public static boolean isActivitiesDownloaded(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("DOWNLOADED", Context.MODE_PRIVATE);
        return sharedPref.getBoolean(context.getString(R.string.SHARED_PREFERENCES_ACTIVITIES_KEY),false);
    }
    public static boolean isShopsDownloaded(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("DOWNLOADED", Context.MODE_PRIVATE);
        return sharedPref.getBoolean(context.getString(R.string.SHARED_PREFERENCES_SHOPS_KEY),false);
    }
}

package com.builders.moodymusic;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.builders.moodymusic.constants.MoodConstants;

public class AppController extends Application {

    public static Context context;
    public static MoodConstants.MOOD currentMood;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}

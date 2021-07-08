package com.builders.moodymusic;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class AppController extends Application {

    public static Context context;

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

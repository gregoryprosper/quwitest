package com.example.quwitest;

import android.app.Application;

public class QuwiApp extends Application {
    private static QuwiApp application;

    public static QuwiApp getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}

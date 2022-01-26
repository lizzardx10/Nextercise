package com.example.nextercise;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Register your parse models
        ParseObject.registerSubclass(Exercise.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("SnUllb3VxCGIqgrM8aphfjiKZZwaLPAhC8dv8seU")
                .clientKey("fOplf25BUNoZ3KSRKxVo6QmmhzwhmX4onGwT50mA")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IoxEwribRqvLOD3a9jhCuM35MX05xAETS2BECjvh")
                // if defined
                .clientKey("Bi0QN6MEEabSUawNVWy56AFBAiAm9wiVFXJR4sEG")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}

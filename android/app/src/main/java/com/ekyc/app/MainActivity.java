package com.ekyc.app;

import android.os.Bundle;

import com.reactnativenavigation.NavigationActivity;

import org.devio.rn.splashscreen.SplashScreen;

public class MainActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SplashScreen.show(this, R.style.SplashScreenThem);
        SplashScreen.show(this);
        super.onCreate(savedInstanceState);
    }

}

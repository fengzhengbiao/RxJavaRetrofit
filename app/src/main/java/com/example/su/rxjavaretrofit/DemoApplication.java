package com.example.su.rxjavaretrofit;

import android.app.Application;
import android.content.Context;

/**
 * Created by su on 2017/5/26.
 */

public class DemoApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }


    public static Context getContext() {
        return sContext;
    }
}

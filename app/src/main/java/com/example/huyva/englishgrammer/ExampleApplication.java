package com.example.huyva.englishgrammer;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by huyva on 1/27/2018.
 */

public class ExampleApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code…
    }
}
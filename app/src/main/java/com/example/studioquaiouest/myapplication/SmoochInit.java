package com.example.studioquaiouest.myapplication;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;
import io.smooch.core.Settings;
import io.smooch.core.Smooch;
import io.smooch.core.SmoochCallback;

/**
 * Created by Administrateur on 01/04/2017.
 */

public class SmoochInit extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Answers(), new Crashlytics());

        //Smooch.init(this, "2eqtejjhiuvokd28rusdr6azd");
        Smooch.init(this, new Settings("58de9e832081126a004078b2"), new SmoochCallback() {
            @Override
            public void run(SmoochCallback.Response response) {
                // Your code after init is complete
            }
        });
    }
}

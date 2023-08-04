package com.wallpaper.wallpaper.Manger;

import android.app.Application;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkSettings;

public class Initialize extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize AppLovin SDK with custom settings
        AppLovinSdk.getInstance(this).initializeSdk(new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK has been initialized
            }
        });
    }
}

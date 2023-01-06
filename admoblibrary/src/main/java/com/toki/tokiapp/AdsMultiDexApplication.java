package com.toki.tokiapp;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.toki.tokiapp.ads.AdmodUtils;
import com.toki.tokiapp.ads.AppOpenManager;

import java.util.List;

public abstract class AdsMultiDexApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

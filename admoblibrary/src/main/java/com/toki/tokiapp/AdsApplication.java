package com.toki.tokiapp;

import android.app.Application;

import com.toki.tokiapp.ads.AdmodUtils;
import com.toki.tokiapp.ads.AppOpenManager;

import java.util.List;

public abstract class AdsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AdmodUtils.getInstance().initAdmob(this, 10000,false, true);
        if(enableAdsResume()) {
            AppOpenManager.getInstance().init(this, getOpenAppAdId());
        }
    }

    public abstract boolean enableAdsResume();

    public abstract String getOpenAppAdId();
}

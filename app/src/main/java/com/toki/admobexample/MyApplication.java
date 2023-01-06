package com.toki.admobexample;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.toki.tokiapp.AdsMultiDexApplication;
import com.toki.tokiapp.ads.AdmodUtils;
import com.toki.tokiapp.ads.AppOpenManager;
import com.toki.tokiapp.iap.PurchaseUtils;

public class MyApplication extends AdsMultiDexApplication {
    boolean isShowAds = true;
    boolean isShowAdsResume = true;

    @Override
    public void onCreate() {
        super.onCreate();

        PurchaseUtils.getInstance().initBilling(this,getString(R.string.play_console_license));

//        if (PurchaseUtils.getInstance().isSubscriptiond(getString(R.string.premium))) {
//            isShowAds = false;
//        }else {
//            isShowAds = true;
//        }
        PurchaseUtils.getInstance().isPurchased(getString(R.string.product_id));

        AdmodUtils.getInstance().initAdmob(this, 10000,true, isShowAds);
        if (isShowAdsResume) {
            AppOpenManager.getInstance().init(this, getString(R.string.test_ads_admob_app_open));
//            AppOpenManager.getInstance().disableAppResumeWithActivity(SplashActivity.class);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

package com.toki.admobexample;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.toki.admobexample.view.MainActivity;
import com.toki.admobexample.view.OtherActivity;
import com.toki.tokiapp.ads.AdCallbackNew;
import com.toki.tokiapp.ads.AdLoadCallback;
import com.toki.tokiapp.ads.AppOpenManager;
import com.toki.tokiapp.utils.Utils;
import com.toki.tokiapp.ads.AdCallback;
import com.toki.tokiapp.ads.AdmodUtils;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        AppCompatButton btn_next = findViewById(R.id.btn_next);
        AdmodUtils.getInstance().loadAndShowAdInterstitialWithCallbackMultiAds(this, "", "", "", new AdCallback() {
            @Override
            public void onAdClosed() {

                Utils.getInstance().replaceActivity(SplashActivity.this, MainActivity.class);
            }

            @Override
            public void onAdFail() {
                Utils.getInstance().replaceActivity(SplashActivity.this, MainActivity.class);

            }
        },false);
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppOpenManager.getInstance().showAdIfAvailable(false);
//                if(AdmodUtils.getInstance().mInterstitialAd != null) {
//                    AdmodUtils.getInstance().showAdInterstitialWithCallbackNotLoad(AdmodUtils.getInstance().mInterstitialAd, SplashActivity.this, new AdCallbackNew() {
//                        @Override
//                        public void onAdClosed() {
//                            Utils.getInstance().replaceActivity(SplashActivity.this, MainActivity.class);
//                        }
//
//                        @Override
//                        public void onAdFail() {
//                            Utils.getInstance().replaceActivity(SplashActivity.this, MainActivity.class);
//                        }
//
//                        @Override
//                        public void onEventClickAdClosed() {
//                            Log.e("===splash","onEventClickAdClosed");
//                        }
//
//                        @Override
//                        public void onAdShowed() {
//                            Log.e("===splash","onAdShowed");
//
//                        }
//                    });
//                } else{
//                    Utils.getInstance().replaceActivity(SplashActivity.this, MainActivity.class);
//                }
            }
        }

//        AdmodUtils.getInstance().loadAdInterstitial(this, getString(R.string.test_ads_admob_inter_id), new AdLoadCallback() {
//            @Override
//            public void onAdFail() {
//                btn_next.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAdLoaded() {
//                btn_next.setVisibility(View.VISIBLE);
//            }
//        },false);


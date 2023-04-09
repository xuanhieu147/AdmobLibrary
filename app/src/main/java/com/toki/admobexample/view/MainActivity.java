package com.toki.admobexample.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codemybrainsout.ratingdialog.MaybeLaterCallback;
import com.codemybrainsout.ratingdialog.RatingDialog;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.nativead.NativeAd;
import com.toki.admobexample.SplashActivity;
import com.toki.admobexample.utilsdemp.AdsManager;
import com.toki.admobexample.utilsdemp.UtilsDemoActivity;
import com.toki.tokiapp.ads.AdCallbackNew;
import com.toki.tokiapp.ads.AdLoadCallback;
import com.toki.tokiapp.ads.NativeAdCallback;
import com.toki.tokiapp.ads.admobnative.enumclass.GoogleEBanner;
import com.toki.tokiapp.ads.admobnative.enumclass.GoogleENative;
import com.toki.admobexample.R;
import com.toki.admobexample.iap.IAPActivity;
import com.toki.tokiapp.ads.model.AdUnitListModel;
import com.toki.tokiapp.ads.model.AdsConfigModel;
import com.toki.tokiapp.utils.Utils;
import com.toki.tokiapp.ads.AdCallback;
import com.toki.tokiapp.ads.AdmodUtils;
import com.toki.tokiapp.ads.RewardAdCallback;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn_LoadInter, btn_ShowInter;
    Button btn_LoadAndShowInter, btn_LoadAndShowReward;
    Button btn_LoadInterReward, btn_ShowInterReward;
    Button btn_LoadNative, btn_LoadNativeGrid;
    Button btn_IAP, btn_Rate, btn_Utils;
    LinearLayout nativeAds;
    FrameLayout banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        finbyid();
        showDialogRate();

        // AdsConfigModel = Model call by API
//         Utils.getInstance().adUnitLists = adsConfigModel.getAdUnitList();

        //API data sample
        AdUnitListModel adUnitList = Utils.getInstance().getAdUnitByName("Name AdUnit", "Defaul Id Admob");
        //check Countries (BOOL)

//        if (Utils.getInstance().showAdForCountry(this,adUnitList)){
//            //show ads
//        }else{
//            //dont show ads
//        }

        btn_Utils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UtilsDemoActivity.class));
            }
        });
        btn_LoadInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdsManager.INSTANCE.loadInter(MainActivity.this, AdsManager.INSTANCE.getInterholder());
            }
        });
        btn_ShowInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.INSTANCE.showInter(MainActivity.this, AdsManager.INSTANCE.getInterholder(), new AdsManager.AdListener() {

                    @Override
                    public void onFailed() {
                        Utils.getInstance().addActivity(MainActivity.this, OtherActivity.class);
                    }

                    @Override
                    public void onAdClosed() {
                        Utils.getInstance().addActivity(MainActivity.this, OtherActivity.class);
                    }
                }, true);

            }
        });
        btn_LoadAndShowInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdmodUtils.getInstance().loadAndShowAdInterstitialWithCallback(MainActivity.this, "ca-app-pub-3940256099942544/1033173712", 0, new AdCallbackNew() {
                    @Override
                    public void onAdClosed() {
                        AdmodUtils.getInstance().dismissAdDialog();
                        Utils.getInstance().addActivity(MainActivity.this, OtherActivity.class);

                    }

                    @Override
                    public void onEventClickAdClosed() {

                    }

                    @Override
                    public void onAdShowed() {

                    }

                    @Override
                    public void onAdLoaded() {

                    }

                    @Override
                    public void onAdFail() {
                        onAdClosed();
                    }
                }, true);
            }
        });
        btn_LoadAndShowReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdmodUtils.getInstance().loadAndShowAdRewardWithCallback(MainActivity.this, getString(R.string.test_ads_admob_reward_id), new RewardAdCallback() {
                    @Override
                    public void onAdClosed() {
                        if (AdmodUtils.getInstance().mRewardedAd != null) {
                            AdmodUtils.getInstance().mRewardedAd = null;
                        }
                        AdmodUtils.getInstance().dismissAdDialog();
                        //Utils.getInstance().showMessenger(MainActivity.this, "close ad");
                        startActivity(new Intent(MainActivity.this, OtherActivity.class));
                    }

                    @Override
                    public void onEarned() {
                        if (AdmodUtils.getInstance().mRewardedAd != null) {
                            AdmodUtils.getInstance().mRewardedAd = null;
                        }
                        AdmodUtils.getInstance().dismissAdDialog();
                        Utils.getInstance().showMessenger(MainActivity.this, "Reward");

                    }

                    @Override
                    public void onAdFail() {
                        //code here
                        Utils.getInstance().showMessenger(MainActivity.this, "Reward fail");
                    }
                }, true);

            }
        });
        btn_LoadNative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().addActivity(MainActivity.this, NativeRecyclerActivity.class);
            }
        });
        btn_LoadNativeGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.getInstance().addActivity(MainActivity.this, NativeGridActivity.class);
            }
        });
        btn_LoadInterReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdmodUtils.getInstance().loadAdInterstitialReward(MainActivity.this, getString(R.string.test_ads_admob_inter_reward_id), new AdLoadCallback() {
                    @Override
                    public void onAdFail() {
                        Utils.getInstance().addActivity(MainActivity.this, OtherActivity.class);
                        Utils.getInstance().showMessenger(MainActivity.this, "onAdFail");

                    }

                    @Override
                    public void onAdLoaded() {
                        Utils.getInstance().showMessenger(MainActivity.this, "show popup");
                        //show dialog
                    }
                });
            }

        });

        btn_ShowInterReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AdmodUtils.getInstance().mInterstitialRewardAd != null) {
                    AdmodUtils.getInstance().showAdInterstitialRewardWithCallback(AdmodUtils.getInstance().mInterstitialRewardAd, MainActivity.this, new RewardAdCallback() {
                        @Override
                        public void onAdClosed() {
                            Utils.getInstance().addActivity(MainActivity.this, OtherActivity.class);
                        }

                        @Override
                        public void onAdFail() {
                            Utils.getInstance().showMessenger(MainActivity.this, "onAdFail");
                            Utils.getInstance().addActivity(MainActivity.this, OtherActivity.class);
                        }

                        @Override
                        public void onEarned() {
                            Utils.getInstance().showMessenger(MainActivity.this, "onEarned");
                            //bool true
                        }
                    });
                } else {
                    Utils.getInstance().replaceActivity(MainActivity.this, OtherActivity.class);

                }
            }
        });

        btn_IAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().addActivity(MainActivity.this, IAPActivity.class);
            }
        });
        btn_Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRate();
            }
        });
//        AdmodUtils.getInstance().loadNativeAdsWithLayout(MainActivity.this, getString(R.string.test_ads_admob_native_id), nativeAds, R.layout.ad_unified_medium);


        AdmodUtils.getInstance().loadAndShowNativeAdsWithLayout(MainActivity.this, getString(R.string.test_ads_admob_native_id), nativeAds, R.layout.ad_template_medium, GoogleENative.UNIFIED_MEDIUM, new NativeAdCallback() {
            @Override
            public void onNativeAdLoaded() {
            }

            @Override
            public void onAdFail() {

            }

            @Override
            public void onAdPaid(AdValue adValue) {
                Utils.getInstance().showMessenger(MainActivity.this, adValue.toString());
            }

            @Override
            public void onLoadedAndGetNativeAd(NativeAd ad) {

            }
        });
        AdmodUtils.getInstance().loadAdBanner(MainActivity.this, getString(R.string.test_ads_admob_banner_id), banner, new AdmodUtils.BannerCallBack() {
            @Override
            public void onLoad() {
                Utils.getInstance().showMessenger(MainActivity.this, "onLoad");
            }

            @Override
            public void onFailed() {
                Utils.getInstance().showMessenger(MainActivity.this, "onFailed");
            }

            @Override
            public void onPaid(AdValue adValue) {
                Utils.getInstance().showMessenger(MainActivity.this, adValue.toString());
            }
        });
    }

    private void showDialogRate() {
        RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .session(1)
                .date(1)
                .setNameApp(getString(R.string.app_name))
                .setIcon(R.mipmap.ic_launcher)
                .setEmail("vapp.helpcenter@gmail.com")
                .isShowButtonLater(true)
                .isClickLaterDismiss(true)
                .setTextButtonLater("Maybe Later")
                .setOnlickMaybeLate(new MaybeLaterCallback() {
                    @Override
                    public void onClick() {
                        Utils.getInstance().showMessenger(MainActivity.this, "clicked Maybe Later");
                    }
                })
                .ignoreRated(true)
                .ratingButtonColor(R.color.purple_200)
                .build();

        //Cancel On Touch Outside
        ratingDialog.setCanceledOnTouchOutside(false);
        //show
        ratingDialog.show();


        //thêm vào activity trong manifest
//        <intent-filter>
//            <action android:name="android.intent.action.SENDTO" />
//            <data android:scheme="mailto" />
//            <category android:name="android.intent.category.DEFAULT" />
//        </intent-filter>

        // thêm vào activity
//        android:windowSoftInputMode="adjustPan|adjustResize"

    }

    void finbyid() {
        btn_Utils = findViewById(R.id.btn_Utils);
        btn_LoadInter = findViewById(R.id.btn_LoadInter);
        btn_ShowInter = findViewById(R.id.btn_ShowInter);
        btn_LoadAndShowInter = findViewById(R.id.btn_LoadAndShowInter);
        btn_LoadAndShowReward = findViewById(R.id.btn_LoadAndShowReward);
        btn_LoadNative = findViewById(R.id.btn_LoadNative);
        nativeAds = findViewById(R.id.nativeAds);
        banner = findViewById(R.id.banner);
        btn_IAP = findViewById(R.id.btn_IAP);
        btn_Rate = findViewById(R.id.btn_Rate);
        btn_LoadNativeGrid = findViewById(R.id.btn_LoadNativeGrid);

        btn_LoadInterReward = findViewById(R.id.btn_LoadInterReward);
        btn_ShowInterReward = findViewById(R.id.btn_ShowInterReward);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
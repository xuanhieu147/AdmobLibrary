package com.toki.admobexample.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.toki.admobexample.R;
import com.toki.tokiapp.ads.AdmodUtils;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        if (AdmodUtils.getInstance().dialog != null && AdmodUtils.getInstance().dialog.getDialog() != null) {
            if (AdmodUtils.getInstance().dialog.getDialog().isShowing()) {
                AdmodUtils.getInstance().dialog.dismiss();
            }
        }


    }
}

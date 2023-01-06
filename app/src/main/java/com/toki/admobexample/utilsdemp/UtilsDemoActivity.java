package com.toki.admobexample.utilsdemp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.toki.admobexample.R;
import com.toki.tokiapp.ads.AdmodUtils;
import com.toki.tokiapp.iap.PurchaseUtils;
import com.toki.tokiapp.iap.SkuDetailsModel;
import com.toki.tokiapp.utils.DialogCallback;
import com.toki.tokiapp.utils.DialogType;
import com.toki.tokiapp.utils.Utils;

public class UtilsDemoActivity extends AppCompatActivity {

    private Button btn_open_dialog;
    private Button btn_open_dialog2;
    private Button btn_open_dialog3, btn_open_dialog4;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils);

        btn_open_dialog = findViewById(R.id.btn_open_dialog);
        btn_open_dialog2 = findViewById(R.id.btn_open_dialog2);
        btn_open_dialog3 = findViewById(R.id.btn_open_dialog3);
        btn_open_dialog4 = findViewById(R.id.btn_open_dialog4);



        btn_open_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//          Context context, String title, String content, String lableButton, DialogType dialogType,
//          boolean isEnableCancel, String lableCancel, DialogCallback dialogCallback)

          Utils.getInstance().showDialogTitle(UtilsDemoActivity.this, "Tiêu đề", "Nội dung", "oke"
                  , DialogType.NORMAL_TYPE,true,"đóng", new DialogCallback() {
                   @Override
                   public void onClosed() {

                   }

                      @Override
                      public void cancel() {

                      }
                  });
            }
        });

        btn_open_dialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().showDialogTitle(UtilsDemoActivity.this, "Tiêu đề", "Nội dung", "oke"
                        , DialogType.ERROR_TYPE,true,"đóng", new DialogCallback() {
                            @Override
                            public void onClosed() {

                            }

                            @Override
                            public void cancel() {

                            }
                        });
            }
        });

        btn_open_dialog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().showDialogTitle(UtilsDemoActivity.this, "Tiêu đề", "Nội dung", "oke"
                        , DialogType.SUCCESS_TYPE,true,"đóng", new DialogCallback() {
                            @Override
                            public void onClosed() {

                            }

                            @Override
                            public void cancel() {

                            }
                        });
            }
        });

        btn_open_dialog4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().showDialogTitle(UtilsDemoActivity.this, "Tiêu đề", "Nội dung", "oke"
                        , DialogType.WARNING_TYPE,true,"đóng", new DialogCallback() {
                            @Override
                            public void onClosed() {

                            }

                            @Override
                            public void cancel() {

                            }
                        });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}

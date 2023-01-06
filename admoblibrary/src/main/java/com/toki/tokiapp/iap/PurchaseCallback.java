package com.toki.tokiapp.iap;

public interface PurchaseCallback {
    void onSkuDetailsResponse(SkuDetailsModel model);
    void onSkuDetailsError(String error);
}

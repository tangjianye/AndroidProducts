package com.product.takeaway;

import android.os.Bundle;

import com.product.takeaway.base.BaseWebActivity;

public class TakeawayActivity extends BaseWebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // loadUrl("file:///android_asset/js/index.html");
        loadUrl("http://192.168.0.75:8100/");
    }
}

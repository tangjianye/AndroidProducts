package com.product.takeaway;

import android.os.Bundle;

import com.product.takeaway.base.BaseWebActivity;

public class MainActivity extends BaseWebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // loadUrl("file:///android_asset/js/index.html");
        loadUrl("https://aorise-org.github.io/takeaway/");
    }
}

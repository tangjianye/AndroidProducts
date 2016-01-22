package com.peach.colorfulnote.ui.activity;

import android.os.Bundle;

import com.peach.colorfulnote.common.Constants;
import com.peach.colorfulnote.ui.base.BaseWebActivity;

/**
 * Created by Administrator on 2015/11/26 0026.
 */
public class WebViewActivity extends BaseWebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = (String) getIntent().getSerializableExtra(Constants.SPKey.BUNDLE_URL);
        loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package cn.aorise.sample.ui.activity;

import android.os.Bundle;

import cn.aorise.common.core.ui.base.BaseWebActivity;
import cn.aorise.sample.config.Constant;


/**
 * Created by Administrator on 2015/11/26 0026.
 */
public class WebViewActivity extends BaseWebActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        loadUrl(Constant.ABOUT_URI);
    }
}

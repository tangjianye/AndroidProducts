package cn.aorise.education.ui.activity;

import android.os.Bundle;

import cn.aorise.common.core.ui.base.BaseWebActivity;
import cn.aorise.education.config.Constant;


/**
 * Created by Administrator on 2015/11/26 0026.
 */
public class AboutActivity extends BaseWebActivity {
    private static final String TAG = AboutActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(Constant.ABOUT_URI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

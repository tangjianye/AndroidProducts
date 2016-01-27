package com.product.masktime.ui.activity;

import android.os.Bundle;
import android.view.ViewStub;

import com.product.common.utils.SPUtils;
import com.product.masktime.R;
import com.product.masktime.common.AppManager;
import com.product.masktime.common.Constants;
import com.product.masktime.ui.base.BaseActivity;
import com.product.masktime.ui.layer.GuideLayer;
import com.product.masktime.ui.layer.WelcomeLayer;

public class SplashActivity extends BaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private WelcomeLayer mWelcomeLayer = null;
    private GuideLayer mGuideLayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 防止用户点击状态栏重新激活app
        boolean isAppLive = AppManager.getInstance().resumeApp(this);
        super.onCreate(savedInstanceState);
        if (isAppLive) {
            finish();
            return;
        }

        setContentView(R.layout.activity_welcome);
        switchView(getGuideSwitch());
    }

    private Boolean getGuideSwitch() {
        return (Boolean) SPUtils.get(this, Constants.SPKey.GUIDE_KEY, false);
    }

    private void switchView(Boolean havedShowGuide) {
        if (havedShowGuide) {
            ViewStub stub = (ViewStub) findViewById(R.id.vs_welcome);
            stub.inflate();
            mWelcomeLayer = (WelcomeLayer) findViewById(R.id.in_welcome);
            mWelcomeLayer.refresh(null);
        } else {
            ViewStub stub = (ViewStub) findViewById(R.id.vs_guide);
            stub.inflate();
            mGuideLayer = (GuideLayer) findViewById(R.id.in_guide);
            mGuideLayer.refresh(null);

            SPUtils.put(this, Constants.SPKey.GUIDE_KEY, true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mWelcomeLayer) {
            mWelcomeLayer.destroy();
        }
        if (null != mGuideLayer) {
            mGuideLayer.destroy();
        }
    }
}

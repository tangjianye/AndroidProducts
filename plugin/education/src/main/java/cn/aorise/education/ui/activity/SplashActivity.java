package cn.aorise.education.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import cn.aorise.common.core.utils.handler.HandlerUtil;
import cn.aorise.education.R;
import cn.aorise.education.databinding.EducationActivitySplashBinding;
import cn.aorise.education.ui.base.EducationBaseActivity;

@Deprecated
public class SplashActivity extends EducationBaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final int DELAY_MILLIS = 1500;
    private EducationActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 防止用户点击状态栏重新激活app
//        boolean isAppLive = ActivityManager.getInstance().resumeApp(this);
//        super.onCreate(savedInstanceState);
//        if (isAppLive) {
//            finish();
//            return;
//        }

        super.onCreate(savedInstanceState);
        HandlerUtil.runOnUiThreadDelay(new Runnable() {
            @Override
            public void run() {
                openActivity(MainActivity.class);
                finish();
            }
        }, DELAY_MILLIS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.education_activity_splash);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HandlerUtil.HANDLER.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

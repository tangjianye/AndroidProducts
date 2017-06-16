package cn.aorise.education.ui.activity;

import cn.aorise.education.R;
import cn.aorise.common.component.login.AoriseSplashActivity;

@Deprecated
public class SplashV2Activity extends AoriseSplashActivity {
    private static final String TAG = SplashV2Activity.class.getSimpleName();

    @Override
    protected int getBackgroundResource() {
        return R.drawable.aorise_bg_splash;
    }
}

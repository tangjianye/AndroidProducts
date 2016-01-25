package com.product.masktime;

import android.app.Application;

import com.product.masktime.common.AppManager;
import com.product.masktime.db.DBManager;
import com.product.masktime.module.image.ImageLoaderManager;
import com.product.masktime.module.net.VolleyManager;
import com.product.masktime.ui.notification.Notify;

public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        // CrashException.getInstance().init(this);
        DBManager.getInstance().init(this);
        ImageLoaderManager.getInstance().init(this);
        VolleyManager.getInstance().init(this);
        Notify.getInstance().init(this);

        // CommonUtils.getChannel(this);
        // CommonUtils.getAppInfo(this);
    }

    public void exitApp(boolean isKillProcess) {
        AppManager.getInstance().appExit(this, isKillProcess);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

package com.peach.colorfulnote;

import android.app.Application;

import com.peach.colorfulnote.common.AppManager;
import com.peach.colorfulnote.db.DBManager;
import com.peach.colorfulnote.module.image.ImageLoaderManager;
import com.peach.colorfulnote.module.net.VolleyManager;
import com.peach.colorfulnote.ui.notification.Notify;

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

        // CommUtils.getChannel(this);
        // CommUtils.getAppInfo(this);
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

package cn.aorise.platform;

import android.app.Application;
import android.util.Log;

import cn.aorise.common.BaseApplication;
import cn.aorise.common.core.manager.AppManager;
import cn.aorise.petition.PetitionApplication;

public class PlatformApplication extends BaseApplication {
    private static final String TAG = PlatformApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        AppManager.getInstance().add(new PetitionApplication());
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void create(Application context) {
        super.create(context);
        Log.i(TAG, "init");
    }

    @Override
    public void destroy(Application context, boolean isKillProcess) {
        super.destroy(context, isKillProcess);
        Log.i(TAG, "destroy");
    }
}

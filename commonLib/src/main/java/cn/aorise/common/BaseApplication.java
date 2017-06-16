package cn.aorise.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import cn.aorise.common.core.interfaces.IAppCycle;
import cn.aorise.common.core.manager.ActivityManager;
import cn.aorise.common.core.manager.AppManager;
import cn.aorise.common.core.module.glide.GlideManager;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.DebugUtil;

public class BaseApplication extends Application implements IAppCycle {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        create(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void create(Application context) {
        Log.i(TAG, "create");
        DebugUtil.syncIsDebug(context);
        // AppManager.getInstance().add(context);
        // AoriseLog.init(BuildConfig.LOG_DEBUG, BuildConfig.APPLICATION_ID);
        AppManager.getInstance().createAll(this);
    }

    @Override
    public void destroy(Application context, boolean isKillProcess) {
        Log.i(TAG, "destroy");
        // MobclickAgent.onKillProcess(this);
        GlideManager.getInstance().shutDown(context);
        ActivityManager.getInstance().appExit(context);
        RxAPIManager.getInstance().cancelAll();

        AppManager.getInstance().destroyAll(context, isKillProcess);
        if (isKillProcess) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}

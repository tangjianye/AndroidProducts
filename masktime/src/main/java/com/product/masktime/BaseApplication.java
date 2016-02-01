package com.product.masktime;

import android.app.Application;

import com.product.common.utils.LogUtils;
import com.product.masktime.common.AppManager;
import com.product.masktime.db.DBManager;
import com.product.masktime.module.fresco.FrescoManager;
import com.product.masktime.module.net.VolleyManager;
import com.product.masktime.module.uil.ImageLoaderManager;
import com.product.masktime.thridpart.statistics.StatisticsProxy;
import com.product.masktime.thridpart.update.UpdateProxy;
import com.product.masktime.ui.notification.Notify;
import com.umeng.analytics.MobclickAgent;

public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
        init();
    }

    private void initStetho() {
//        Stetho.initialize(Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                .build());

//        Stetho.initializeWithDefaults(this);
    }

    private void init() {
        LogUtils.init(BuildConfig.LOG_DEBUG, BuildConfig.APPLICATION_ID);
        // CrashException.getInstance().init(this);

        DBManager.getInstance().init(this);
        ImageLoaderManager.getInstance().init(this);
        FrescoManager.getInstance().init(this);
        VolleyManager.getInstance().init(this);
        Notify.getInstance().init(this);

        StatisticsProxy.getInstance().init(this);
        UpdateProxy.getInstance().init(this);
    }

    public void exitApp(boolean isKillProcess) {
        MobclickAgent.onKillProcess(this);
        ImageLoaderManager.getInstance().shutDown();
        FrescoManager.getInstance().shutDown();
        VolleyManager.getInstance().shutDown();
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

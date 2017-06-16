package cn.aorise.sample;

import android.app.Application;
import android.util.Log;

import cn.aorise.common.core.interfaces.IAppCycle;
import cn.aorise.sample.db.DbHelper;

public class SampleApplication implements IAppCycle {
    private static final String TAG = SampleApplication.class.getSimpleName();

    @Override
    public void create(Application context) {
        Log.i(TAG, "init");
        DbHelper.getInstance().init(context);

    }

    @Override
    public void destroy(Application context, boolean isKillProcess) {
        DbHelper.getInstance().close();
    }
}

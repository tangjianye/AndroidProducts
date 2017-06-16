package cn.aorise.hospital;

import android.app.Application;
import android.util.Log;

import cn.aorise.common.core.interfaces.IAppCycle;
import cn.aorise.hospital.db.DbHelper;

public class HospitalApplication implements IAppCycle {
    private static final String TAG = HospitalApplication.class.getSimpleName();

    @Override
    public void create(Application context) {
        Log.i(TAG, "init");
        // AoriseLog.init(DebugUtil.isDebug(), BuildConfig.APPLICATION_ID);
        DbHelper.getInstance().init(context);

    }

    @Override
    public void destroy(Application context, boolean isKillProcess) {
        DbHelper.getInstance().close();
    }
}

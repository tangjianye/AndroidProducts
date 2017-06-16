package cn.aorise.petition;

import android.app.Application;
import android.util.Log;

import cn.aorise.common.core.interfaces.IAppCycle;

public class PetitionApplication implements IAppCycle {
    private static final String TAG = PetitionApplication.class.getSimpleName();

    @Override
    public void create(Application context) {
        Log.i(TAG, "init");
        // AoriseLog.init(DebugUtil.isDebug(), BuildConfig.APPLICATION_ID);
        // DbHelper.getInstance().init(context);

    }

    @Override
    public void destroy(Application context, boolean isKillProcess) {
        // DbHelper.getInstance().close();
    }
}

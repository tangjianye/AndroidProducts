package cn.aorise.petition;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.interfaces.IAppCycle;
import cn.aorise.common.core.utils.assist.DebugUtil;
import cn.aorise.petition.config.Config;
import cn.aorise.petition.ui.bean.Petition_contact_people;

public class PetitionApplication implements IAppCycle {
    private static final String TAG = PetitionApplication.class.getSimpleName();


    @Override
    public void create(Application context) {
        Log.i(TAG, "init");
        // AoriseLog.init(DebugUtil.isDebug(), BuildConfig.APPLICATION_ID);
        // DbHelper.getInstance().init(context);
        DebugUtil.setDebug(Config.sIsDebug);
    }

    @Override
    public void destroy(Application context, boolean isKillProcess) {
        // DbHelper.getInstance().close();
    }
}

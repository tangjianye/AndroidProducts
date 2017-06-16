package cn.aorise.education;

import android.app.Application;

import cn.aorise.common.core.interfaces.IAppCycle;

public class EducationApplication implements IAppCycle {
    private static final String TAG = EducationApplication.class.getSimpleName();

    @Override
    public void create(Application context) {

    }

    @Override
    public void destroy(Application context, boolean isKillProcess) {

    }
}

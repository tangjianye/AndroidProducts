package cn.aorise.common.core.manager;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.interfaces.IAppCycle;

/**
 * Created by tangjy on 2017/3/21.
 */

public class AppManager {
    private static AppManager sInstance;
    private List<IAppCycle> sList;

    private AppManager() {
        sList = new ArrayList<>();
    }

    public static AppManager getInstance() {
        if (sInstance == null) {
            synchronized (AppManager.class) {
                if (sInstance == null) {
                    sInstance = new AppManager();
                }
            }
        }
        return sInstance;
    }

    public void add(IAppCycle appCycle) {
        sList.add(appCycle);
    }

    public void remove(IAppCycle appCycle) {
        sList.remove(appCycle);
    }

    public void clear() {
        sList.clear();
    }

    public List<IAppCycle> getList() {
        return sList;
    }


    public void createAll(Application context) {
        for (IAppCycle appCycle : sList) {
            appCycle.create(context);
        }
    }


    public void destroyAll(Application context, boolean isKillProcess) {
        for (IAppCycle appCycle : sList) {
            appCycle.destroy(context, isKillProcess);
        }
    }
}

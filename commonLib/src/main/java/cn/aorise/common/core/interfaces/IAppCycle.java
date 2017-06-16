package cn.aorise.common.core.interfaces;

import android.app.Application;

/**
 * Created by tangjy on 2017/3/21.
 */
public interface IAppCycle {
    void create(Application context);

    void destroy(Application context, boolean isKillProcess);
}

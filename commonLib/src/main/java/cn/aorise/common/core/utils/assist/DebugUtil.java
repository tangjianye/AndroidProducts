package cn.aorise.common.core.utils.assist;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * AoriseUtil for App
 * <ul>
 * <li>{@link #syncIsDebug(Context)} Should be called in module Application</li>
 * </ul>
 * Created by Trinea on 2017/3/9.
 */
public class DebugUtil {
    private static Boolean isDebug = null;

    public static boolean isDebug() {
        return isDebug == null ? false : isDebug.booleanValue();
    }

    /**
     * Sync lib debug with app's debug value. Should be called in module Application
     *
     * @param context
     */
    public static void syncIsDebug(Context context) {
        if (isDebug == null) {
            isDebug = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
    }

    public static void setDebug(Boolean isDebug) {
        DebugUtil.isDebug = isDebug;
    }
}

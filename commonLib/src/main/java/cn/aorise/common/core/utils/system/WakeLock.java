package cn.aorise.common.core.utils.system;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

/**
 * 亮屏
 */
public class WakeLock {
    PowerManager powerManager;
    PowerManager.WakeLock wakeLock;

    public WakeLock(Context context, String tag) {
        ////获取电源的服务 声明电源管理器
        powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, tag);
    }

    /**
     * Call requires API level 7
     */
    public boolean isScreenOn() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ECLAIR_MR1) {
            // AoriseLog.e("can not call isScreenOn if SDK_INT < 7 ");
            return false;
        } else {
            return powerManager.isScreenOn();
        }
    }

    public void turnScreenOn() {
        //点亮亮屏
        // AoriseLog.i("PowerManager.WakeLock : wakeLock.isHeld: " + wakeLock.isHeld());
        if (!wakeLock.isHeld()) {
            // AoriseLog.i("PowerManager.WakeLock : 点亮屏幕");
            wakeLock.acquire();
        }
    }

    public void turnScreenOff() {
        //释放亮屏
        // AoriseLog.i("PowerManager.WakeLock : wakeLock.isHeld: " + wakeLock.isHeld());
        if (wakeLock.isHeld()) {
            // AoriseLog.i("PowerManager.WakeLock : 灭掉屏幕");
            try {
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
                // AoriseLog.e(e);
            }
        }
    }

    public void release() {
        if (wakeLock != null && wakeLock.isHeld()) {
            try {
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
                // AoriseLog.e(e);
            }
        }
    }

    public PowerManager.WakeLock getWakeLock() {
        return wakeLock;
    }

    public void setWakeLock(PowerManager.WakeLock wakeLock) {
        this.wakeLock = wakeLock;
    }

    public PowerManager getPowerManager() {
        return powerManager;
    }

    public void setPowerManager(PowerManager powerManager) {
        this.powerManager = powerManager;
    }
}

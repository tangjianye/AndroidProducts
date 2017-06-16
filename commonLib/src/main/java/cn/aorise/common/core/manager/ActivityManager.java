package cn.aorise.common.core.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * 页面管理
 */
public class ActivityManager {
    private static Stack<Activity> sActivityStack;
    private static ActivityManager sInstance;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (sInstance == null) {
            synchronized (ActivityManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<Activity>();
        }
        sActivityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public Activity currentActivity() {
        if (sActivityStack == null || sActivityStack.isEmpty()) {
            return null;
        }
        return sActivityStack.lastElement();
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public Activity findActivity(Class<?> cls) {
        Activity activity = null;
        for (Activity aty : sActivityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity() {
        Activity activity = sActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (sActivityStack == null) return;
        for (int i = 0, size = sActivityStack.size(); i < size; i++) {
            if (null != sActivityStack.get(i)) {
                sActivityStack.get(i).finish();
            }
        }
        sActivityStack.clear();
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls) {
        for (Activity activity : sActivityStack) {
            if (!(activity.getClass().equals(cls))) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 应用程序退出
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * 恢复当前APP页面
     *
     * @param context
     * @return
     */
    public boolean resumeApp(Context context) {
        boolean isAppLive = false;
        Activity activity = currentActivity();
        if (activity != null) {
            isAppLive = true;
            Intent intent = new Intent(context, activity.getClass());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        }
        return isAppLive;
    }
}

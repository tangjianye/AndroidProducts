package cn.aorise.sample.common;

import java.lang.reflect.Type;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.common.core.utils.assist.DebugUtil;
import rx.Subscriber;

/**
 * Created by Administrator on 2015/11/6 0006.
 */
public class Utils {
    private static final String TAG = "sample.Utils";

    /**
     * common
     **********************************************************************************************/


    /**
     * private
     **********************************************************************************************/

    /**
     * 创建联网请求mock模式
     *
     * @param activity
     * @param mockPath
     * @param aClass
     * @param callback
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Subscriber<T> mockSubscriber(BaseActivity activity, String mockPath, Class<T> aClass, APICallback<T> callback) {
        AoriseLog.i(TAG, "DebugUtil.isDebug() = " + DebugUtil.isDebug());
        return AoriseUtil.mockSubscriber(DebugUtil.isDebug(), activity, mockPath, aClass, callback);
    }

    /**
     * 创建联网请求mock模式
     *
     * @param activity
     * @param mockPath
     * @param typeOfT
     * @param callback
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Subscriber<T> mockSubscriber(BaseActivity activity, String mockPath, Type typeOfT, APICallback<T> callback) {
        AoriseLog.i(TAG, "DebugUtil.isDebug() = " + DebugUtil.isDebug());
        return AoriseUtil.mockSubscriber(DebugUtil.isDebug(), activity, mockPath, typeOfT, callback);
    }
}

package cn.aorise.common.core.utils.assist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;

import cn.aorise.common.R;
import cn.aorise.common.core.config.AoriseConstant;
import cn.aorise.common.core.module.glide.GlideManager;
import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIMockSubscriber;
import cn.aorise.common.core.module.network.APISubscriber;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.file.FileUtil;
import cn.aorise.common.core.utils.file.SdCardUtil;
import cn.aorise.common.core.utils.system.AppUtil;
import rx.Subscriber;

/**
 * Created by Administrator on 2015/11/6 0006.
 */
public class AoriseUtil {
    private static final String TAG = AoriseUtil.class.getSimpleName();

    /**
     * common
     **********************************************************************************************/
    public static String getSdCard() {
        // String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        String sdcard = SdCardUtil.getNormalSDCardPath();
        AoriseLog.i(TAG, "sdcard = " + sdcard);
        return sdcard;
    }

    public static String getRootPath(String root) {
        String absolutePath = getSdCard() + File.separator + root;
        AoriseLog.i(TAG, "getRootPath = " + absolutePath);
        return absolutePath;
    }

    public static String getSdPath(String root, String relPath) {
        String absolutePath = getRootPath(root) + File.separator + relPath;
        AoriseLog.i(TAG, "getSdPath = " + absolutePath);
        return absolutePath;
    }

    private static void makeFolders(String root) {
        if (SdCardUtil.isSdCardAvailable()) {
            try {
                FileUtil.forceMkdir(new File(getRootPath(root)));
                FileUtil.forceMkdir(new File(getSdPath(root, AoriseConstant.Folder.LOG_PATH)));
                FileUtil.forceMkdir(new File(getSdPath(root, AoriseConstant.Folder.CACHE_PATH)));
                FileUtil.forceMkdir(new File(getSdPath(root, AoriseConstant.Folder.DOWNLOAD_PATH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AoriseLog.i(TAG, "T卡无效");
        }
    }

    public static void initAppEnvironment(String root) {
        makeFolders(root);
    }

    /**
     * private
     **********************************************************************************************/

    /**
     * 获取应用渠道ID
     *
     * @param context 上下文
     * @return
     */
    public static String getChannel(Context context) {
        String channel = AppUtil.getAppSource(context, AoriseConstant.CHANNEL);
        AoriseLog.i(TAG, "channel = " + channel);
        return channel;
    }

    /**
     * 设置图片显示
     *
     * @param context
     * @param view
     * @param uri
     * @param placeholder
     * @param error
     */
    public static <T> void loadImage(final Context context, ImageView view, T uri,
                                     @DrawableRes int placeholder, @DrawableRes int error) {
        GlideManager.getInstance().load(context, view, uri, placeholder, error);
    }

    /**
     * 设置图片显示
     *
     * @param context
     * @param view
     * @param uri
     */
    public static <T> void loadImage(final Context context, ImageView view, T uri) {
        GlideManager.getInstance().load(context, view, uri);
    }

    /**
     * 设置播放信息
     *
     * @param context
     * @param bundle
     * @param cls
     * @return
     */
    public static Intent getMaskIntent(Context context, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent();
        // intent.putExtra(AoriseConstant.TransportKey.INTENT_KEY, bundle);
        intent.putExtras(bundle);
        intent.setClass(context.getApplicationContext(), cls);
        return intent;
    }

    public static Intent getMaskIntent(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * 获取数据
     *
     * @param intent
     * @return
     */
    public static Serializable getMaskSerializable(Intent intent) {
        if (null != intent) {
            // Bundle bundle = intent.getBundleExtra(AoriseConstant.TransportKey.INTENT_KEY);
            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                return bundle.getSerializable(AoriseConstant.TransportKey.BUNDLE_KEY);
            }
        }
        return null;
    }

    /**
     * 绑定数据
     *
     * @param value
     * @return
     */
    public static Bundle getMaskBundle(Serializable value) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AoriseConstant.TransportKey.BUNDLE_KEY, value);
        return bundle;
    }

    /**
     * 创建联网请求mock模式
     *
     * @param mock
     * @param activity
     * @param mockPath
     * @param aClass
     * @param callback
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Subscriber<T> mockSubscriber(boolean mock, BaseActivity activity, String mockPath, Class<T> aClass, APICallback<T> callback) {
        Subscriber subscriber;
        if (mock && !TextUtils.isEmpty(mockPath)) {
            subscriber = new APIMockSubscriber(activity, mockPath, aClass, callback);
        } else {
            subscriber = new APISubscriber(activity, callback);
        }
        return subscriber;
    }

    /**
     * 创建联网请求mock模式
     *
     * @param mock
     * @param activity
     * @param mockPath
     * @param typeOfT
     * @param callback
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Subscriber<T> mockSubscriber(boolean mock, BaseActivity activity, String mockPath, Type typeOfT, APICallback<T> callback) {
        Subscriber subscriber;
        if (mock && !TextUtils.isEmpty(mockPath)) {
            subscriber = new APIMockSubscriber(activity, mockPath, typeOfT, callback);
        } else {
            subscriber = new APISubscriber(activity, callback);
        }
        return subscriber;
    }

    public static View inflateLayout(Activity context, @LayoutRes int resource, @Nullable ViewGroup root) {
        // return LayoutInflater.from(context).inflate(R.layout.aorise_include_empty_tips, null);
        return LayoutInflater.from(context).inflate(resource, root, false);
    }

    public static View inflateEmptyView(Activity context, @Nullable ViewGroup root) {
        return inflateLayout(context, R.layout.aorise_include_empty_tips, root);
    }

    public static View inflateFooterView(Activity context, @Nullable ViewGroup root) {
        return inflateLayout(context, R.layout.aorise_include_load_more, root);
    }
}

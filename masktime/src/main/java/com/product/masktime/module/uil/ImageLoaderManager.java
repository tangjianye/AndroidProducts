package com.product.masktime.module.uil;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.product.common.utils.LogUtils;

/**
 * Volley网络通信单例管理类<br>
 * Created by tangjy on 2015/3/2.
 */
public class ImageLoaderManager {
    private static final String TAG = ImageLoaderManager.class.getSimpleName();
    private static Context sCtx;
    private static ImageLoaderManager sINSTANTCE;

    private ImageLoaderManager() {
    }

    public static synchronized ImageLoaderManager getInstance() {
        if (sINSTANTCE == null) {
            sINSTANTCE = new ImageLoaderManager();
        }
        return sINSTANTCE;
    }

    public void init(Context context) {
        if (!(context instanceof Application)) {
            throw new AssertionError();
        }

        sCtx = context;
        ImageLoaderConfig.initImageLoader(context, null);
    }

    public void loadImage(ImageView view, String uri) {
        loadImage(view, uri, mLoadingListener);
    }

    public void loadImage(ImageView view, String uri, ImageLoadingListener listener) {
        ImageLoaderConfig.getImageLoader().displayImage(uri, view, ImageLoaderConfig.getDefaultOptions(), listener);
    }

    public void shutDown() {
        ImageLoaderConfig.getImageLoader().clearMemoryCache();
    }

    private ImageLoadingListener mLoadingListener = new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String imageUri, View view) {
            LogUtils.i(TAG, "imageUri = " + imageUri);
        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            LogUtils.i(TAG, "imageUri = " + imageUri);
        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            LogUtils.i(TAG, "imageUri = " + imageUri);
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            LogUtils.i(TAG, "imageUri = " + imageUri);
        }
    };
}

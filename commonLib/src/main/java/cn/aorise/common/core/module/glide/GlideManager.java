package cn.aorise.common.core.module.glide;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import cn.aorise.common.core.utils.assist.AoriseLog;

/**
 * Created by pc on 2017/3/7.
 */
public class GlideManager {
    private static final String TAG = GlideManager.class.getSimpleName();

    private static GlideManager ourInstance = new GlideManager();

    public static GlideManager getInstance() {
        return ourInstance;
    }

    private GlideManager() {
    }

    public <T> void load(final Context context, ImageView view, T uri) {
        Glide.with(context)
                .load(uri)
                .listener(getRequestListener())
                .into(view);
    }

    public <T> void load(final Context context, ImageView view, T uri, @DrawableRes int placeholder, @DrawableRes int error) {
        Glide.with(context)
                .load(uri)
                .placeholder(placeholder)
                .error(error)
                .listener(getRequestListener())
                .into(view);
    }

    @NonNull
    private <T> RequestListener<T, GlideDrawable> getRequestListener() {
        return new RequestListener<T, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, T model, Target<GlideDrawable> target, boolean isFirstResource) {
                AoriseLog.e(TAG, e.toString());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, T model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                AoriseLog.e(TAG, "onResourceReady");
                return false;
            }
        };
    }

    public void shutDown(final Context context) {
        Glide.get(context).clearMemory();
    }
}

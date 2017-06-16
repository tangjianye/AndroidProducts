package cn.aorise.common.core.module.network;

import java.lang.ref.WeakReference;

import cn.aorise.common.R;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.Network;
import rx.Subscriber;

public class APISubscriber<T> extends Subscriber<T> {
    private static final String TAG = APISubscriber.class.getSimpleName();
    protected WeakReference<BaseActivity> mWeakReference;
    protected APICallback<T> mCallback;

    public APISubscriber(BaseActivity activity, APICallback<T> callback) {
        mWeakReference = new WeakReference<>(activity);
        mCallback = callback;
    }

    @Override
    public void onStart() {
        super.onStart();
        AoriseLog.i(TAG, "onStart");
        if (mWeakReference.get() != null) {
            if (Network.isAvailable(mWeakReference.get())) {
                mWeakReference.get().showLoadingDialog();
            } else {
                mWeakReference.get().showToast(R.string.aorise_label_no_net);
            }
        }
        mCallback.onStart();
    }

    @Override
    public void onCompleted() {
        AoriseLog.i(TAG, "onCompleted");
        if (mWeakReference.get() != null) {
            mWeakReference.get().dismissLoadingDialog();
        }
        mCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        AoriseLog.e(TAG, e.toString());
        if (mWeakReference.get() != null) {
            mWeakReference.get().dismissLoadingDialog();
        }
        mCallback.onError(e);
    }

    @Override
    public void onNext(T t) {
        AoriseLog.i(TAG, "onNext");
        if (null == t) {
            AoriseLog.i(TAG, "onNext is null");
        } else {
            AoriseLog.i(TAG, t.toString());
        }
        if (mWeakReference.get() != null) {
            mWeakReference.get().dismissLoadingDialog();
        }
        mCallback.onNext(t);
    }
}

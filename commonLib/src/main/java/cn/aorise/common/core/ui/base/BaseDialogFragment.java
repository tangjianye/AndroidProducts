package cn.aorise.common.core.ui.base;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import cn.aorise.common.R;


/**
 * Created by tangjy on 2016-8-29.
 */
@Deprecated
public abstract class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        int width = getResources().getDimensionPixelOffset(R.dimen.aorise_dialog_width);
        getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException ignore) {
        }
    }

    /**
     * 为获取接口类型定义的一个辅助方法 简化每次都要强转的麻烦
     *
     * @param listenerInterface
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T getDialogListener(Class<T> listenerInterface) {
        //用targetFragment是否为空来标识是fragment还是activity开启的这个DialogFragment
        final Fragment targetFragment = getTargetFragment();
        if (targetFragment != null && listenerInterface.isAssignableFrom(targetFragment.getClass())) {
            return (T) targetFragment;
        }
        if (getActivity() != null && listenerInterface.isAssignableFrom(getActivity().getClass())) {
            return ((T) getActivity());
        }
        return null;
    }
}

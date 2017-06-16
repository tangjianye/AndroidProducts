package cn.aorise.common.core.ui.base;

import android.support.v4.app.Fragment;

/**
 * Created by pc on 2017/3/1.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
//        BaseApplication.getRefWatcher(getActivity()).watch(this);
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        BaseApplication.getRefWatcher(getActivity()).watch(this);
//    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}

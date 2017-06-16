package cn.aorise.petition.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionFragmentHomeBinding;
import cn.aorise.petition.ui.base.PetitionBaseFragment;

/**
 * Created by pc on 2017/3/2.
 */
public class HomeFragment extends PetitionBaseFragment {
    private PetitionFragmentHomeBinding mBinding;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.petition_fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

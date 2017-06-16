package cn.aorise.petition.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionFragmentPersonalBinding;
import cn.aorise.petition.ui.base.PetitionBaseFragment;

/**
 * Created by pc on 2017/3/2.
 */
public class PersonalFragment extends PetitionBaseFragment {
    private PetitionFragmentPersonalBinding mBinding;

    public PersonalFragment() {
    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.petition_fragment_personal, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

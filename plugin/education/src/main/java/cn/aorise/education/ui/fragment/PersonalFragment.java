package cn.aorise.education.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.aorise.education.R;
import cn.aorise.education.databinding.EducationFragmentPersonalBinding;
import cn.aorise.education.ui.base.EducationBaseFragment;

/**
 * Created by pc on 2017/3/2.
 */
public class PersonalFragment extends EducationBaseFragment {
    private EducationFragmentPersonalBinding mBinding;

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.education_fragment_personal, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

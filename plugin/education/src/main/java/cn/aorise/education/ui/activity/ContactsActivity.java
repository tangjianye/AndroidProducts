package cn.aorise.education.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import cn.aorise.education.R;
import cn.aorise.education.databinding.EducationActivityContactsBinding;
import cn.aorise.education.ui.base.EducationBaseActivity;

public class ContactsActivity extends EducationBaseActivity {
    private static final String TAG = ContactsActivity.class.getSimpleName();
    private EducationActivityContactsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.education_activity_contacts);
    }

    @Override
    protected void initEvent() {

    }
}

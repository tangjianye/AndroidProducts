package cn.aorise.petition.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityContactsBinding;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

public class ContactsActivity extends PetitionBaseActivity {
    private static final String TAG = ContactsActivity.class.getSimpleName();
    private PetitionActivityContactsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.petition_activity_contacts);
    }

    @Override
    protected void initEvent() {

    }
}

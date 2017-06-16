package cn.aorise.petition.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionGuideNoticeMatterBinding;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

/**
 * Created by Administrator on 2017/5/16.
 */

public class GuideNoticeMatterActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionGuideNoticeMatterBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_guide_notice_matter);

    }

    @Override
    protected void initEvent() {

    }
}

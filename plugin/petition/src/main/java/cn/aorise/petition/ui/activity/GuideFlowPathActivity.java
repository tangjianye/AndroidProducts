package cn.aorise.petition.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionGuideFlowPathBinding;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

/**
 * Created by Administrator on 2017/5/16.
 */

public class GuideFlowPathActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionGuideFlowPathBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_has_answer==id) {
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_02);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));//设置点击按钮颜色变化
            mBinding.imageView22.setVisibility(View.VISIBLE);
            mBinding.imageView23.setVisibility(View.GONE);


        } else if (R.id.rl_not_answer==id) {
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_02);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.imageView22.setVisibility(View.GONE);
            mBinding.imageView23.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_guide_flow_path);
        mBinding.rlHasAnswer.setOnClickListener(this);
        mBinding.rlNotAnswer.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }
}

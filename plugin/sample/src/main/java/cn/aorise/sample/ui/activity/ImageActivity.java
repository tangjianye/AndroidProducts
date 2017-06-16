package cn.aorise.sample.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.sample.R;
import cn.aorise.sample.databinding.SampleActivityImageBinding;
import cn.aorise.sample.ui.base.SampleBaseActivity;

public class ImageActivity extends SampleBaseActivity implements View.OnClickListener {
    private static final String TAG = ImageActivity.class.getSimpleName();
    private SampleActivityImageBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.sample_activity_image);
        mBinding.btnGetImage.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.btn_get_image == id) {
            AoriseUtil.loadImage(this, mBinding.ivGlide, "http://nuuneoi.com/uploads/source/playstore/cover.jpg");
            // AoriseUtil.loadImage(this, mBinding.ivGlide, R.mipmap.ic_launcher);
        } else {
            AoriseLog.i(TAG, "onClick");
        }
    }
}

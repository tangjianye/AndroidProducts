package cn.aorise.sample.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.sample.BuildConfig;
import cn.aorise.sample.R;
import cn.aorise.sample.databinding.SampleActivityMainBinding;
import cn.aorise.sample.ui.base.SampleBaseActivity;

public class MainActivity extends SampleBaseActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SampleActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AoriseUtil.initAppEnvironment(BuildConfig.APPLICATION_ID);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.sample_activity_main);
        getToolBar().setNavigationIcon(null);

        mBinding.btnImage.setOnClickListener(this);
        mBinding.btnNetwork.setOnClickListener(this);
        mBinding.btnList.setOnClickListener(this);
        mBinding.btnListNet.setOnClickListener(this);
        mBinding.btnDatabase.setOnClickListener(this);
        mBinding.btnWeb.setOnClickListener(this);
        mBinding.btnCrash.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (R.id.btn_image == id) {
            openActivity(ImageActivity.class);
        } else if (R.id.btn_network == id) {
            openActivity(NetworkActivity.class);
        } else if (R.id.btn_list == id) {
            openActivity(ListActivity.class);
        } else if (R.id.btn_list_net == id) {
            openActivity(NetListV2Activity.class);
        } else if (R.id.btn_database == id) {
            openActivity(DbActivity.class);
        } else if (R.id.btn_web == id) {
            openActivity(WebViewActivity.class);
        } else if (R.id.btn_crash == id) {
            foreCrash();
        } else {
            AoriseLog.i(TAG, "onClick");
        }
    }

    private void foreCrash() {
        int crash = 10 / 0;
    }
}

package cn.aorise.hospital.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.hospital.R;
import cn.aorise.hospital.databinding.HospitalActivityMainBinding;
import cn.aorise.hospital.ui.base.HospitalBaseActivity;

public class MainActivity extends HospitalBaseActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private HospitalActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AoriseUtil.initAppEnvironment(getPackageName());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.hospital_activity_main);
        getToolBar().setNavigationIcon(null);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

//        if (R.id.btn_image == id) {
//            openActivity(ImageActivity.class);
//        } else if (R.id.btn_network == id) {
//            openActivity(NetworkActivity.class);
//        } else if (R.id.btn_list == id) {
//            openActivity(ListActivity.class);
//        } else if (R.id.btn_list_net == id) {
//            openActivity(NetListV2Activity.class);
//        } else if (R.id.btn_database == id) {
//            openActivity(DbActivity.class);
//        } else if (R.id.btn_web == id) {
//            openActivity(WebViewActivity.class);
//        } else if (R.id.btn_crash == id) {
//            foreCrash();
//        } else {
//            AoriseLog.i(TAG, "onClick");
//        }
    }

    private void foreCrash() {
        int crash = 10 / 0;
    }
}

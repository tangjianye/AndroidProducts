package cn.aorise.platform.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;

import cn.aorise.common.core.config.AoriseConstant;
import cn.aorise.common.core.manager.ActivityManager;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.platform.BuildConfig;
import cn.aorise.platform.R;
import cn.aorise.platform.databinding.PlatformActivityMainBinding;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    // 时间间隔
    private static final long EXIT_INTERVAL = 2000L;
    // 需要监听几次点击事件数组的长度就为几
    // 如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
    private long[] mHints = new long[2];
    private PlatformActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        getLoginInfo();
    }

    private void getLoginInfo() {
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = intent.getExtras();
            String account = bundle.getString(AoriseConstant.AccountKey.USER_ACCOUNT);
            String id = bundle.getString(AoriseConstant.AccountKey.USER_ID);
            String sex = bundle.getString(AoriseConstant.AccountKey.USER_SEX);
            AoriseLog.i("Account:" + account + " ;ID:" + id + " ;Sex:" + sex);

            if (BuildConfig.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("Account:").append(account).append("\r\n").append("ID:").append(id).append("\r\n")
                        .append("Sex:").append(sex);
                showToast(sb.toString());
            }
        }
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.platform_activity_main);
        mBinding.btnEducation.setOnClickListener(this);
        mBinding.btnHospital.setOnClickListener(this);
        mBinding.btnPetition.setOnClickListener(this);
        mBinding.btnSecurity.setOnClickListener(this);
        mBinding.btnOther.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_education:
                //openActivity(cn.aorise.education.ui.activity.MainActivity.class);
                break;
            case R.id.btn_hospital:
                //openActivity(cn.aorise.hospital.ui.activity.MainActivity.class);
                break;
            case R.id.btn_petition:
                openActivity(cn.aorise.petition.ui.activity.MainActivity.class);
                break;
            case R.id.btn_security:
                //openActivity(NetListV2Activity.class);
                break;
            case R.id.btn_other:
                openActivity(cn.aorise.sample.ui.activity.MainActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 将mHints数组内的所有元素左移一个位置
            System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
            // 获得当前系统已经启动的时间
            mHints[mHints.length - 1] = SystemClock.uptimeMillis();
            if ((SystemClock.uptimeMillis() - mHints[0]) > EXIT_INTERVAL) {
                showToast(getString(cn.aorise.common.R.string.aorise_label_double_exit));
            } else {
                finish();
                ActivityManager.getInstance().appExit(getApplicationContext());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

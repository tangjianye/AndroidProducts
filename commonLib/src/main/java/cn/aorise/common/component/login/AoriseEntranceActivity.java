package cn.aorise.common.component.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import cn.aorise.common.core.config.AoriseConstant;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.DebugUtil;

/**
 * Created by pc on 2017/3/17.
 */
@Deprecated
public abstract class AoriseEntranceActivity extends BaseActivity {
    private static final String TAG = AoriseEntranceActivity.class.getSimpleName();
    private EntranceReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    class EntranceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent
                    && AoriseConstant.BroadcastKey.CN_AORISE_PLATFORM_LOGIN_ACCOUNT.equals(intent.getAction())) {
                Bundle bundle = intent.getExtras();
                String account = bundle.getString(AoriseConstant.AccountKey.USER_ACCOUNT);
                String id = bundle.getString(AoriseConstant.AccountKey.USER_ID);
                String sex = bundle.getString(AoriseConstant.AccountKey.USER_SEX);
                AoriseLog.i("Account:" + account + " ;ID:" + id + " ;Sex:" + sex);

                if (DebugUtil.isDebug()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Account:").append(account).append("\r\n").append("ID:").append(id).append("\r\n")
                            .append("Sex:").append(sex);
                    showToast(sb.toString());
                }
            }
        }
    }

    private void registerReceiver() {
        mReceiver = new EntranceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(AoriseConstant.BroadcastKey.CN_AORISE_PLATFORM_LOGIN_ACCOUNT);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);
    }
}

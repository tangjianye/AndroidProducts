package cn.aorise.common.core.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import cn.aorise.common.core.config.AoriseConstant;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.DebugUtil;

/**
 * 接受广播进入首页
 */
@Deprecated
public class PlatformEntranceReceiver extends BroadcastReceiver {
    private EntranceListener mEntranceListener;

    public PlatformEntranceReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (null != intent
                && AoriseConstant.BroadcastKey.CN_AORISE_PLATFORM_LOGIN_ACCOUNT.equals(action)) {
            Bundle bundle = intent.getExtras();
            String account = bundle.getString(AoriseConstant.AccountKey.USER_ACCOUNT);
            String id = bundle.getString(AoriseConstant.AccountKey.USER_ID);
            String sex = bundle.getString(AoriseConstant.AccountKey.USER_SEX);
            AoriseLog.i("Account:" + account + " ;ID:" + id + " ;Sex:" + sex);

            if (DebugUtil.isDebug()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Account:").append(account).append("\r\n").append("ID:").append(id).append("\r\n")
                        .append("Sex:").append(sex);
                ((BaseActivity) context).showToast(sb.toString());
            }
        }
    }

    public void registerReceiver(Context context, EntranceListener entranceListener) {
        try {
            this.mEntranceListener = entranceListener;
            IntentFilter filter = new IntentFilter();
            filter.addAction(AoriseConstant.BroadcastKey.CN_AORISE_PLATFORM_LOGIN_ACCOUNT);
            context.registerReceiver(this, filter);
            AoriseLog.d("registerScreenReceiver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unRegisterReceiver(Context context) {
        try {
            context.unregisterReceiver(this);
            AoriseLog.d("unRegisterReceiver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface EntranceListener {
        void entrance();
    }
}
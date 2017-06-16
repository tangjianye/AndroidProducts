package cn.aorise.common.component.login;

import android.content.ComponentName;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import cn.aorise.common.R;
import cn.aorise.common.component.common.Utils;
import cn.aorise.common.component.network.AoriseApiService;
import cn.aorise.common.component.network.Mock;
import cn.aorise.common.component.network.entity.response.AccountInfo;
import cn.aorise.common.core.cache.SpCache;
import cn.aorise.common.core.config.AoriseConstant;
import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.system.AppUtil;
import cn.aorise.common.databinding.AoriseActivityComponentLoginBinding;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2017/3/17.
 */
public class AoriseLoginActivity extends BaseActivity {
    private static final String TAG = AoriseLoginActivity.class.getSimpleName();
    private static final String PACKAGE_NAME_KEY = "PACKAGE_NAME_KEY";
    private static final String CLASS_NAME_KEY = "CLASS_NAME_KEY";
    private static final String ACCOUNT = "account";
    private static final String PASSWORD = "password";
    private AoriseActivityComponentLoginBinding mBinding;
    private SpCache mSpCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        RxAPIManager.getInstance().cancel(TAG);
    }

    @Override
    protected void initData() {
        mSpCache = new SpCache(this);
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.aorise_activity_component_login);
        getToolBar().setNavigationIcon(null);

        String account = mSpCache.get(ACCOUNT, "");
        if (!TextUtils.isEmpty(account)) {
            mBinding.etAccount.setText(account);
        }
        String password = mSpCache.get(PASSWORD, "");
        if (!TextUtils.isEmpty(password)) {
            mBinding.etPassword.setText(password);
        }
    }

    @Override
    protected void initEvent() {
        mBinding.etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mBinding.etAccount.setError(null);
        mBinding.etPassword.setError(null);

        // Store values at the time of the login attempt.
        String account = mBinding.etAccount.getText().toString();
        String password = mBinding.etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mBinding.etPassword.setError(getString(R.string.aorise_component_error_field_required));
            focusView = mBinding.etPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mBinding.etPassword.setError(getString(R.string.aorise_component_error_invalid_password));
            focusView = mBinding.etPassword;
            cancel = true;
        }

        // Check for a valid account address.
        if (TextUtils.isEmpty(account)) {
            mBinding.etAccount.setError(getString(R.string.aorise_component_error_field_required));
            focusView = mBinding.etAccount;
            cancel = true;
        } else if (!isAccountValid(account)) {
            mBinding.etAccount.setError(getString(R.string.aorise_component_error_invalid_account));
            focusView = mBinding.etAccount;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            request(account, password);

            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setAccount("AAAAAA");
            accountInfo.setId("123456789");
            accountInfo.setSex("nan");
            success(accountInfo);
        }
    }

    private void saveLoginInfo(String account, String password) {
        mSpCache.put(ACCOUNT, account);
        mSpCache.put(PASSWORD, password);
    }

    private boolean isAccountValid(String account) {
        //TODO: Replace this with your own logic
        return account.length() > 4;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

//    /**
//     * 发送本地广播
//     */
//    @Deprecated
//    private void sendLocalBroadcast() {
//        Intent mIntent = new Intent(AoriseConstant.BroadcastKey.CN_AORISE_PLATFORM_LOGIN_ACCOUNT);
//        mIntent.putExtra(AoriseConstant.AccountKey.USER_ACCOUNT, "奥昇平台");
//        mIntent.putExtra(AoriseConstant.AccountKey.USER_ID, "431125201702133114");
//        mIntent.putExtra(AoriseConstant.AccountKey.USER_SEX, "男");
//        LocalBroadcastManager.getInstance(this).sendBroadcast(mIntent);
//    }

    private void request(String account, String password) {
        Subscription subscription = AoriseApiService.Factory.create().getLogin()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.GET_ACCOUNT
                        , new TypeToken<APIResult<AccountInfo>>() {
                        }.getType()
                        , new APICallback<APIResult<AccountInfo>>() {
                            @Override
                            public void onStart() {
                                AoriseLog.i(TAG, "onStart");
                            }

                            @Override
                            public void onError(Throwable e) {
                                AoriseLog.e(TAG, "onError:" + e.toString());
                            }

                            @Override
                            public void onCompleted() {
                                AoriseLog.i(TAG, "onCompleted");
                            }

                            @Override
                            public void onNext(APIResult<AccountInfo> accountInfoAPIResult) {
                                AoriseLog.i(TAG, "onNext");
                                success(accountInfoAPIResult.getData());
                            }

                            @Override
                            public void onMock(APIResult<AccountInfo> accountInfoAPIResult) {
                                AoriseLog.i(TAG, "onMock");
                                success(accountInfoAPIResult.getData());
                            }
                        }
                ));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

    private void success(AccountInfo accountInfo) {
        saveLoginInfo(mBinding.etAccount.getText().toString(),
                mBinding.etPassword.getText().toString());
        gotoMainActivity(accountInfo);
    }

    private void gotoMainActivity(AccountInfo accountInfo) {
        String packageName = AppUtil.getAppSource(this, PACKAGE_NAME_KEY);
        String className = AppUtil.getAppSource(this, CLASS_NAME_KEY);
        AoriseLog.d("packageName:" + packageName);
        AoriseLog.d("className:" + className);
        if (null == packageName || null == className) {
            AoriseLog.e("请配置首页跳转地址");
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setComponent(new ComponentName(packageName, className));

        Bundle bundle = new Bundle();
        bundle.putString(AoriseConstant.AccountKey.USER_ACCOUNT, accountInfo.getAccount());
        bundle.putString(AoriseConstant.AccountKey.USER_ID, accountInfo.getId());
        bundle.putString(AoriseConstant.AccountKey.USER_SEX, accountInfo.getSex());
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }
}

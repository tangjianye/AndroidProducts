package cn.aorise.petition.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionActivityLoginBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TLogin;
import cn.aorise.petition.module.network.entity.response.RLogin;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/2.
 */

public class LoginActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityLoginBinding mBinding;
    private static final String TAG = AboutActivity.class.getSimpleName();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_login==id) {
            if (mBinding.edtIdNum.getText().toString().length()==0||
                    mBinding.edtPassword.getText().toString().length()==0){
                showToast(R.string.petition_toast_01);
            }else {
                toLogin();
            }
        } else if (R.id.btn_regist==id) {
            Intent intent=new Intent(this,RegistActivity.class);
            startActivity(intent);
        } else if (R.id.btn_forget_password==id) {
            openActivity(LoginForgetPasswordActivity.class);
        }
    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        editor=sp.edit();
    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_login);
        mBinding.btnRegist.setOnClickListener(this);
        mBinding.btnForgetPassword.setOnClickListener(this);
        mBinding.rlLogin.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    private String makeRequest() {
            TLogin tLogin = new TLogin();
            tLogin.setMM(mBinding.edtPassword.getText().toString());
            tLogin.setYHM(mBinding.edtIdNum.getText().toString());
            return GsonUtil.toJson(tLogin);
    }

    private void toLogin() {
        String request = makeRequest();

        Subscription subscription = PetitionApiService.Factory.create().peopleLogin(request)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_LOGIN,
                        new TypeToken<APIResult<RLogin>>() {
                        }.getType(),
                        new APICallback<APIResult<RLogin>>() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }

                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onNext(APIResult<RLogin> rLoginAPIResult) {
                                AoriseLog.i(TAG, rLoginAPIResult.toString());
                                System.out.println(rLoginAPIResult.toString()+rLoginAPIResult.getData());
                                if (getString(R.string.petition_logined).equals(rLoginAPIResult.getMsg().toString())){
                                    showToast(R.string.petition_logined);
                                    editor.putBoolean(getString(R.string.petition_sharepre_islogin),true);
                                    editor.putString(getString(R.string.petition_shareprefers_XFR_XM),rLoginAPIResult.getData().getXM());
                                    editor.putString(getString(R.string.petition_shareprefers_XFR_ZJHM), (String) rLoginAPIResult.getData().getZJH());
                                    System.out.println(rLoginAPIResult.getData().getZJH());
                                    editor.putString(getString(R.string.petition_sharepres_XFR_ZJLX), (String) rLoginAPIResult.getData().getZJLX());
                                    editor.putString(getString(R.string.petition_sharepres_XFR_DHHM), (String) rLoginAPIResult.getData().getDHHM());
                                    editor.putString(getString(R.string.petition_sharepres_XFR_XB), (String) rLoginAPIResult.getData().getXB());
                                    editor.putString(getString(R.string.petition_sharepres_XFR_SZDQ), (String) rLoginAPIResult.getData().getSZDQ());
                                    editor.putString(getString(R.string.petition_sharepres_XFR_XXDZ), (String) rLoginAPIResult.getData().getXXDZ());
                                    editor.commit();
                                    LoginActivity.this.finish();
                                } else {
                                    showToast(rLoginAPIResult.getMsg());
                                }
                             }

                            @Override
                            public void onMock(APIResult<RLogin> rLoginAPIResult) {
                                AoriseLog.i(TAG, rLoginAPIResult.toString());
                                System.out.println(rLoginAPIResult.toString());
                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
}

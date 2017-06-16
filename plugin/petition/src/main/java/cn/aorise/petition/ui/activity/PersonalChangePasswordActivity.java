package cn.aorise.petition.ui.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionActivityChangePasswordBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TChangePassword;
import cn.aorise.petition.module.network.entity.request.TVerificationCode;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/22.
 */

public class PersonalChangePasswordActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityChangePasswordBinding mBinding;
    private static final String TAG = AboutActivity.class.getSimpleName();
    private SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_submit==id) {
            if (mBinding.edtNewPassword.getText().toString().equals(mBinding.edtResurePassword.getText().toString())){
                ChangePassword();
            } else {
                showToast(R.string.petition_error_show);
            }

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_change_password);
        mBinding.rlSubmit.setOnClickListener(this);

    }

    @Override
    protected void initEvent() {

    }
    private void ChangePassword() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        TChangePassword tChangePassword=new TChangePassword();
        tChangePassword.setZJHM(sp.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        tChangePassword.setJMM(mBinding.edtOldPassword.getText().toString());
        tChangePassword.setXMM(mBinding.edtNewPassword.getText().toString());
        System.out.println(GsonUtil.toJson(tChangePassword));
        Subscription subscription = PetitionApiService.Factory.create().ChangePassword(GsonUtil.toJson(tChangePassword))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<RRegister>>() {
                        }.getType(), new APICallback<APIResult<RRegister>>() {
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
                            public void onNext(APIResult<RRegister> rRegisterAPIResult) {
                                showToast(rRegisterAPIResult.getMsg());
                                if (rRegisterAPIResult.getMsg().equals(getString(R.string.petition_password_change_succeed))) {
                                    PersonalChangePasswordActivity.this.finish();
                                    System.out.println("密码修改成功，退出");
                                }
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);

    }
}

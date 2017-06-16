package cn.aorise.petition.ui.activity;

import android.app.Activity;
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
import cn.aorise.petition.databinding.PetitionActivityChangePhonenumberBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TVerifyPassword;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/19.
 */

public class PersonalChangePhoneNumberActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityChangePhonenumberBinding mBinding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String TAG = AboutActivity.class.getSimpleName();
    public  static Activity instance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_next==id) {
            VerifyPassword();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_change_phonenumber);
        mBinding.rlNext.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    private void VerifyPassword(){
        sp=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        editor=sp.edit();
        TVerifyPassword tVerifyPassword=new TVerifyPassword();
        tVerifyPassword.setMM(mBinding.edtPassword.getText().toString());
        tVerifyPassword.setZJHM(sp.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        System.out.println(GsonUtil.toJson(tVerifyPassword));
        Subscription subscription = PetitionApiService.Factory.create().
                VerifyPassword(GsonUtil.toJson(tVerifyPassword))
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
                                if (rRegisterAPIResult.getMsg().equals("密码正确")) {
                                    openActivity(PersonalChangePhoneNumberActivity01.class);
                                } else {
                                    showToast(rRegisterAPIResult.getMsg());
                                }

                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
}

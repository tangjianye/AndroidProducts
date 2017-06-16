package cn.aorise.petition.ui.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionActivityChangePhonenumber01Binding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TChangePhoneNum;
import cn.aorise.petition.module.network.entity.request.TVerificationCode;
import cn.aorise.petition.module.network.entity.request.TVerifyCode;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/19.
 */

public class PersonalChangePhoneNumberActivity01 extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityChangePhonenumber01Binding mBinding;
    private static final String TAG = AboutActivity.class.getSimpleName();
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.txt_verification==id) {
            getVerificationCode();
            timer.start();
        } else if (R.id.rl_submit==id) {
            VerifyCode();
        }
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            mBinding.txtVerification.setText((int) (l / 1000) + "s后重发");
            mBinding.txtVerification.setBackgroundResource(R.drawable.verification_color_false);
            mBinding.txtVerification.setClickable(false);
        }

        @Override
        public void onFinish() {
            mBinding.txtVerification.setClickable(true);
            mBinding.txtVerification.setText("获取验证码");
            mBinding.txtVerification.setBackgroundResource(R.drawable.verification_color_true);

        }
    };
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_change_phonenumber_01);
        mBinding.txtVerification.setOnClickListener(this);
        mBinding.rlSubmit.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    private void getVerificationCode() {
        TVerificationCode tVerificationCode=new TVerificationCode();
        tVerificationCode.setDHHM(mBinding.edtPhoneNumber.getText().toString());
        System.out.println(GsonUtil.toJson(tVerificationCode));
        Subscription subscription = PetitionApiService.Factory.create().GetVerificationCode(GsonUtil.toJson(tVerificationCode))
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
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);

    }
    private void VerifyCode () {

        TVerifyCode tVerifyCode=new TVerifyCode();
        tVerifyCode.setDHHM(mBinding.edtPhoneNumber.getText().toString());
        tVerifyCode.setYZM(mBinding.edtYzm.getText().toString());
        System.out.println(GsonUtil.toJson(tVerifyCode));
        Subscription subscription = PetitionApiService.Factory.create().VerifyCode(GsonUtil.toJson(tVerifyCode))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<RRegister>() {
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
                                if (rRegisterAPIResult.getMsg().equals(getString(R.string.petition_verifycode_true))){
                                    ChangePhoneNum();
                                }
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

    private void ChangePhoneNum () {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        TChangePhoneNum tChangePhoneNum=new TChangePhoneNum();
        tChangePhoneNum.setXSJH(mBinding.edtPhoneNumber.getText().toString());
        tChangePhoneNum.setZJHM(sp.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        System.out.println(GsonUtil.toJson(tChangePhoneNum));
        Subscription subscription = PetitionApiService.Factory.create().ChangePhoneNum(GsonUtil.toJson(tChangePhoneNum))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<RRegister>() {
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
                                if (rRegisterAPIResult.getMsg().equals(getString(R.string.petition_successd))){
                                    showToast(rRegisterAPIResult.getMsg());
                                    PersonalChangePhoneNumberActivity.instance.finish();
                                    PersonalChangePhoneNumberActivity01.this.finish();
                                }
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

}

package cn.aorise.petition.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.CountDownTimer;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionActivityForgetPasswordBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TVerificationCode;
import cn.aorise.petition.module.network.entity.request.TVerifyCode;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/23.
 */

public class LoginForgetPasswordActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityForgetPasswordBinding mBinding;
    private static final String TAG = AboutActivity.class.getSimpleName();
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.txt_verification==id) {
            if (!mBinding.txtVerification.getText().toString().equals("")){
                getVerificationCode();
                timer.start();
            }
        } else if (R.id.rl_next==id) {
            VerifyCode();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_forget_password);
        mBinding.txtVerification.setOnClickListener(this);
        mBinding.rlNext.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

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

    private void getVerificationCode() {
        TVerificationCode tVerificationCode=new TVerificationCode();
        tVerificationCode.setDHHM(mBinding.edtPhonenumber.getText().toString());
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
        tVerifyCode.setDHHM(mBinding.edtPhonenumber.getText().toString());
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

                                    Intent intent=new Intent(LoginForgetPasswordActivity.this,LoginForgetPasswordActivity01.class);
                                    intent.putExtra("DHHM",mBinding.edtPhonenumber.getText().toString());
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
}

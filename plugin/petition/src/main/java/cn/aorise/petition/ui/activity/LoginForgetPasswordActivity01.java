package cn.aorise.petition.ui.activity;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionActivityForgetPassword01Binding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TFoundPassword;
import cn.aorise.petition.module.network.entity.request.TVerifyCode;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/23.
 */

public class LoginForgetPasswordActivity01 extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityForgetPassword01Binding mBinding;
    private static final String TAG = AboutActivity.class.getSimpleName();

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_next==id) {
            if (!mBinding.edtPassword.getText().toString().equals(mBinding.edtResurePassword.getText().toString())){
                showToast(getString(R.string.petition_error_show));
            } else {
                FoundPassword();
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_forget_password_01);
        mBinding.rlNext.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }
    private void FoundPassword () {
        TFoundPassword tFoundPassword=new TFoundPassword();
        tFoundPassword.setDHHM(getIntent().getStringExtra("DHHM"));
        tFoundPassword.setXMM(mBinding.edtPassword.getText().toString());
        System.out.println(GsonUtil.toJson(tFoundPassword));
        Subscription subscription = PetitionApiService.Factory.create().FoundPassword(GsonUtil.toJson(tFoundPassword))
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

                                }
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
}

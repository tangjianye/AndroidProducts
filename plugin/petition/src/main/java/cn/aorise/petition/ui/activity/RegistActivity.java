package cn.aorise.petition.ui.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.opengl.EGLDisplay;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.common.popupwindow.Popuwindow_card_type;
import cn.aorise.petition.common.popupwindow.Popuwindow_sex_choose;
import cn.aorise.petition.databinding.PetitionActivityRegistBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TRegist;
import cn.aorise.petition.module.network.entity.request.TVerificationCode;
import cn.aorise.petition.module.network.entity.request.TVerifyCode;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.module.network.entity.response.Rsubmit;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/2.
 */

public class RegistActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityRegistBinding mBinding;
    private Popuwindow_card_type select_headView;
    private Popuwindow_sex_choose popuwindow_sex_choose;
    private SharedPreferences sp,sp1;
    private SharedPreferences.Editor editor;
    private static final String TAG = AboutActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.btn_id_card==id) {
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petition_type_id_card));
        } else if (R.id.btn_passport==id) {
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petition_type_passport));
        } else if (R.id.btn_hongkong_card==id) {
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petition_type_hongkong_card));
        } else if (R.id.btn_taiwan_card==id) {
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petititon_type_taiwan_card));
        } else if (R.id.rl_card_type==id) {
            setheadview();
        } else if (R.id.rl_sex==id) {
            popuwindow_sex_choose=new Popuwindow_sex_choose(RegistActivity.this,this);
            popuwindow_sex_choose.setOnDismissListener(new poponDismissListener());
            //显示窗口
            popuwindow_sex_choose.showAtLocation(this.findViewById(R.id.txt_botton), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        } else if (R.id.btn_man==id) {
            popuwindow_sex_choose.dismiss();
            backgroundAlpha(1f);
            mBinding.txtSex.setText(getString(R.string.petition_man));
        } else if (R.id.btn_woman==id) {
            popuwindow_sex_choose.dismiss();
            backgroundAlpha(1f);
            mBinding.txtSex.setText(getString(R.string.petition_woman));
        } else if (R.id.rl_area==id) {
            openActivity(RegistAddressActivity.class);
        } else if (R.id.rl_register==id) {
            if (mBinding.txtName.getText().toString().equals("")||
                    mBinding.edtCardnum.getText().toString().equals("")||
                    mBinding.edtDetailaddress.getText().toString().equals("")||
                    mBinding.edtResurePassword.getText().toString().equals("")||
                    mBinding.edtPassword.getText().toString().equals("")||
                    mBinding.edtPhoneNumber.getText().toString().equals("")){
                showToast(getString(R.string.petition_toast_01));
            } else if (!mBinding.edtPassword.getText().toString().equals(mBinding.edtResurePassword.getText()
            .toString())){
                showToast(R.string.petition_showtoast_02);
            }else {
                VerifyCode();
            }
        } else if (R.id.txt_verification==id) {
            if (!mBinding.txtVerification.getText().toString().equals("")){
                getVerificationCode();
                timer.start();

            }
        }
    }


    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        sp1=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        editor=sp.edit();
    }


    @Override
    protected void initView() {

        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_regist);
        mBinding.rlCardType.setOnClickListener(this);
        mBinding.rlSex.setOnClickListener(this);
        mBinding.rlArea.setOnClickListener(this);
        mBinding.rlRegister.setOnClickListener(this);
        mBinding.txtVerification.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.txtAddress.setText(sp.getString(getString(R.string.petition_share_registAddress_QY_SH),"")
        +"-"+sp.getString(getString(R.string.petition_share_registAddress_QY_S),"")
        +"-"+sp.getString(getString(R.string.petition_share_registAddress_QY_X),""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear().commit();
    }

    private void setheadview() {
        //实例化SelectPicPopupWindow
        select_headView = new Popuwindow_card_type(RegistActivity.this,this);
        select_headView.setOnDismissListener(new poponDismissListener());
        //显示窗口
        select_headView.showAtLocation(this.findViewById(R.id.txt_botton), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
    }
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {

        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);

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

    private String makerequest(){
        TRegist tRegist=new TRegist();
        tRegist.setXM(mBinding.txtName.getText().toString());
        tRegist.setXB(mBinding.txtSex.getText().toString());
        tRegist.setZJLX(mBinding.txtCardType.getText().toString());
        tRegist.setZJHM(mBinding.edtCardnum.getText().toString());
        tRegist.setQY_SH(sp.getString(getString(R.string.petition_share_registAddress_QY_SH),""));
        tRegist.setQY_SHDM(sp.getString(getString(R.string.petition_regist_addaddress_QYSHDM),""));
        tRegist.setQY_S(sp.getString(getString(R.string.petition_share_registAddress_QY_S),""));
        tRegist.setQY_SDM(sp.getString(getString(R.string.petition_regist_addaddress_QYSDM),""));
        tRegist.setQY_X(sp.getString(getString(R.string.petition_share_registAddress_QY_X),""));
        tRegist.setQY_XDM(sp.getString(getString(R.string.petition_regist_addaddress_QYXDM),""));
        tRegist.setXXDZ(mBinding.edtDetailaddress.getText().toString());
        tRegist.setMM(mBinding.edtResurePassword.getText().toString());
        tRegist.setDHHM(mBinding.edtPhoneNumber.getText().toString());
        return GsonUtil.toJson(tRegist);
    }

    private void Register () {
        makerequest();
        System.out.println(makerequest());
        Subscription subscription = PetitionApiService.Factory.create().Register(makerequest())
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
                                RegistActivity.this.finish();
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
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
                                    Register();
                                }
                            }

                            @Override
                            public void onMock(APIResult<RRegister> rRegisterAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
    /*private void getVerificationCode1(final String strRequest) {
        new Thread() {
            @Override
            public void run() {

                try {
                    String url = "http://192.168.1.40:1111/DictionaryInfo/problemType";
                    HttpPost httpPost = new HttpPost(url);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("strRequest", strRequest));


                    HttpResponse httpResponse = null;
                    httpPost.setEntity(new UrlEncodedFormEntity(params, org.apache.http.protocol.HTTP.UTF_8));
                    httpResponse = new DefaultHttpClient().execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        // 第三步，使用getEntity方法活得返回结果
                        String result = EntityUtils.toString(httpResponse.getEntity());
                        JSONObject jsonObject = new JSONObject(result);

                        System.out.println("1111111111"+result);


                    } else {

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }*/
}

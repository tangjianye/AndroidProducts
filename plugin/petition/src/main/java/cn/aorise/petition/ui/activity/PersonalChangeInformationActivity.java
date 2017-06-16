package cn.aorise.petition.ui.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.PopupWindow;

import com.google.gson.reflect.TypeToken;

import java.util.Calendar;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.common.popupwindow.Popuwindow_card_type;
import cn.aorise.petition.common.popupwindow.Popuwindow_sex_choose;
import cn.aorise.petition.databinding.PetitionPersonalChangeInformationBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TQueryEvaluate;
import cn.aorise.petition.module.network.entity.request.TSubmitPersonalInfo;
import cn.aorise.petition.module.network.entity.request.TZJHM;
import cn.aorise.petition.module.network.entity.response.RPersonalBaseInfo;
import cn.aorise.petition.module.network.entity.response.RQueryEvaluate;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;

/**
 * Created by Administrator on 2017/5/17.
 */

public class PersonalChangeInformationActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionPersonalChangeInformationBinding mBinding;
    private SharedPreferences sp,sp1;
    private SharedPreferences.Editor editor;
    private Popuwindow_card_type select_headView;
    private Popuwindow_sex_choose popuwindow_sex_choose;
    private static final String TAG = AboutActivity.class.getSimpleName();
    private String qy_sh,qy_shdm,qy_s,qy_sdm,qy_x,qy_xdm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetPersonalBaseInfo();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_personal_change_information);
        mBinding.rlHasAnswer.setOnClickListener(this);
        mBinding.rlNotAnswer.setOnClickListener(this);
        mBinding.rlDate.setOnClickListener(this);
        mBinding.rlCardType.setOnClickListener(this);
        mBinding.rlSex.setOnClickListener(this);
        mBinding.rlArea01.setOnClickListener(this);
        mBinding.rlNation.setOnClickListener(this);
        mBinding.rlRegister.setOnClickListener(this);
        mBinding.rlRegister01.setOnClickListener(this);
        setView();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_has_answer==id) {//填写基本信息relayout
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_02);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));//设置点击按钮颜色变化

            mBinding.srcBaseInfo.setVisibility(View.VISIBLE);
            mBinding.srcOtherInfo.setVisibility(GONE);

        } else if (R.id.rl_not_answer==id) {//补充信息relayout
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_02);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));

            mBinding.srcBaseInfo.setVisibility(GONE);
            mBinding.srcOtherInfo.setVisibility(View.VISIBLE);

        } else if (R.id.rl_register==id||R.id.rl_register_01==id) {//保存按钮
            getEvaluate();
        } else if (R.id.rl_date==id) {//出生日期选择
            final Calendar c = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(PersonalChangeInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    c.set(year, monthOfYear, dayOfMonth);
                    mBinding.txtAddress.setText(DateFormat.format("yyy-MM-dd", c));
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        } else if (R.id.rl_sex==id) {//性别relayout
            popuwindow_sex_choose=new Popuwindow_sex_choose(PersonalChangeInformationActivity.this,this);
            popuwindow_sex_choose.setOnDismissListener(new poponDismissListener());
            //显示窗口
            popuwindow_sex_choose.showAtLocation(this.findViewById(R.id.rl_register), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        } else if (R.id.rl_card_type==id) {//证件类型relayout
            setheadview();
        }else if (R.id.btn_man==id) {//性别选择
            popuwindow_sex_choose.dismiss();
            backgroundAlpha(1f);
            mBinding.txtSex.setText(getString(R.string.petition_man));
        } else if (R.id.btn_woman==id) {//性别选择
            popuwindow_sex_choose.dismiss();
            backgroundAlpha(1f);
            mBinding.txtSex.setText(getString(R.string.petition_woman));
        }else if (R.id.btn_id_card==id) {//证件类型选择
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petition_type_id_card));
        } else if (R.id.btn_passport==id) {//证件类型选择
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petition_type_passport));
        } else if (R.id.btn_hongkong_card==id) {//证件类型选择
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petition_type_hongkong_card));
        } else if (R.id.btn_taiwan_card==id) {//证件类型选择
            select_headView.dismiss();
            backgroundAlpha(1f);
            mBinding.txtCardType.setText(getString(R.string.petititon_type_taiwan_card));
        } else if (R.id.rl_area01==id) {//地区选择
            Intent intent=new Intent(PersonalChangeInformationActivity.this,PersonalChangeInformationAddressActivity.class);
            startActivity(intent);
        }  else if (R.id.rl_nation==id) {//民族选择
            Intent intent=new Intent(PersonalChangeInformationActivity.this,PersonalChangeInformationNationChooseActivity.class);
            startActivity(intent);
        }
    }

    private void setView(){
        sp=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        sp1=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        editor=sp1.edit();
        /*mBinding.txtName.setText(sp.getString(getString(R.string.petition_shareprefers_XFR_XM),""));
        mBinding.txtCardType.setText(sp.getString(getString(R.string.petition_sharepres_XFR_ZJLX),""));
        mBinding.edtCardnum.setText(sp.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        mBinding.edtPhoneNumber.setText(sp.getString(getString(R.string.petition_sharepres_XFR_DHHM),""));
        mBinding.txtSex.setText(sp.getString(getString(R.string.petition_sharepres_XFR_XB),""));
        mBinding.txtArea.setText(sp.getString(getString(R.string.petition_sharepres_XFR_SZDQ),""));
        mBinding.edtDetailaddress.setText(sp.getString(getString(R.string.petition_sharepres_XFR_XXDZ),""));*/
        mBinding.edtPhoneNumber.setEnabled(false);
    }
    private void setheadview() {
        //实例化SelectPicPopupWindow
        select_headView = new Popuwindow_card_type(PersonalChangeInformationActivity.this,this);
        select_headView.setOnDismissListener(new poponDismissListener());
        //显示窗口
        select_headView.showAtLocation(this.findViewById(R.id.rl_register), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
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

    @Override
    protected void onResume() {
        super.onResume();
        if (!sp1.getString(getString(R.string.petition_share_registAddress_QY_SH),"").equals("")) {
            mBinding.txtArea.setText(sp1.getString(getString(R.string.petition_share_registAddress_QY_SH), "")
                    + "-" + sp1.getString(getString(R.string.petition_share_registAddress_QY_S), "")
                    + "-" + sp1.getString(getString(R.string.petition_share_registAddress_QY_X), ""));
        }
        if (!sp1.getString(getString(R.string.petition_nation_01),"").equals("")) {
            mBinding.txtNation.setText(sp1.getString(getString(R.string.petition_nation_01), ""));
        }else {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear().commit();
    }

    private String Makerequest(){
        TSubmitPersonalInfo tSubmitPersonalInfo=new TSubmitPersonalInfo();
        tSubmitPersonalInfo.setXM(mBinding.txtName.getText().toString());
        tSubmitPersonalInfo.setZJLX(mBinding.txtCardType.getText().toString());
        tSubmitPersonalInfo.setZJHM(mBinding.edtCardnum.getText().toString());
        tSubmitPersonalInfo.setDHHM(mBinding.edtPhoneNumber.getText().toString());
        tSubmitPersonalInfo.setCSRQ(mBinding.txtAddress.getText().toString());
        tSubmitPersonalInfo.setXB(mBinding.txtSex.getText().toString());
        if (!sp1.getString(getString(R.string.petition_regist_addaddress_QYSHDM),"").equals("")){
            tSubmitPersonalInfo.setQY_SHDM(sp1.getString(getString(R.string.petition_regist_addaddress_QYSHDM),""));
            tSubmitPersonalInfo.setQY_SH(sp1.getString(getString(R.string.petition_share_registAddress_QY_SH),""));
            tSubmitPersonalInfo.setQY_SDM(sp1.getString(getString(R.string.petition_regist_addaddress_QYSDM),""));
            tSubmitPersonalInfo.setQY_S(sp1.getString(getString(R.string.petition_share_registAddress_QY_S),""));
            tSubmitPersonalInfo.setQY_XDM(sp1.getString(getString(R.string.petition_regist_addaddress_QYXDM),""));
            tSubmitPersonalInfo.setQY_X(sp1.getString(getString(R.string.petition_share_registAddress_QY_X),""));
        } else {
            tSubmitPersonalInfo.setQY_SHDM(qy_shdm);
            tSubmitPersonalInfo.setQY_SH(qy_sh);
            tSubmitPersonalInfo.setQY_SDM(qy_sdm);
            tSubmitPersonalInfo.setQY_S(qy_s);
            tSubmitPersonalInfo.setQY_XDM(qy_xdm);
            tSubmitPersonalInfo.setQY_X(qy_x);
        }

        tSubmitPersonalInfo.setXXDZ(mBinding.edtDetailaddress.getText().toString());
        if (!sp1.getString(getString(R.string.petition_nation_01),"").equals("")){
            tSubmitPersonalInfo.setMZ(sp1.getString(getString(R.string.petition_nation_01),""));
        } else {
            tSubmitPersonalInfo.setMZ(mBinding.txtNation.getText().toString());
            System.out.println("民族"+mBinding.txtNation.getText().toString());
        }

        tSubmitPersonalInfo.setHKSZD(mBinding.edtHukousuozaidi.getText().toString());
        tSubmitPersonalInfo.setZY(mBinding.edtOccupation.getText().toString());
        tSubmitPersonalInfo.setGZDW(mBinding.edtWorkUnit.getText().toString());
        tSubmitPersonalInfo.setGDDZ(mBinding.edtFixdeAddress.getText().toString());
        tSubmitPersonalInfo.setTXDZ(mBinding.edtMailingAddress.getText().toString());
        tSubmitPersonalInfo.setYB(mBinding.edtZipCode.getText().toString());
        tSubmitPersonalInfo.setDZYX(mBinding.edtEmail.getText().toString());
        return GsonUtil.toJson(tSubmitPersonalInfo);
    }

    private void getEvaluate(){
        System.out.println(Makerequest());
        Subscription subscription = PetitionApiService.Factory.create().
                SubmitPersonalInfo(Makerequest())
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
                                if (rRegisterAPIResult.getMsg().equals("修改成功")) {
                                    PersonalChangeInformationActivity.this.finish();

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

    private void GetPersonalBaseInfo(){
        TZJHM tzjhm=new TZJHM();
        tzjhm.setZJHM(sp.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        System.out.println(GsonUtil.toJson(tzjhm));
        Subscription subscription = PetitionApiService.Factory.create().
                GetPeopleBaseInfo(GsonUtil.toJson(tzjhm))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<RPersonalBaseInfo>>() {
                        }.getType(),
                        new APICallback<APIResult<RPersonalBaseInfo>>() {
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
                            public void onNext(APIResult<RPersonalBaseInfo> rPersonalBaseInfoAPIResult) {

                                mBinding.txtName.setText(rPersonalBaseInfoAPIResult.getData().getXM());
                                mBinding.txtCardType.setText(rPersonalBaseInfoAPIResult.getData().getZJLX());
                                mBinding.edtCardnum.setText(rPersonalBaseInfoAPIResult.getData().getZJHM());
                                mBinding.edtPhoneNumber.setText(rPersonalBaseInfoAPIResult.getData().getDHHM());
                                mBinding.txtAddress.setText(rPersonalBaseInfoAPIResult.getData().getCSRQ());
                                mBinding.txtSex.setText(rPersonalBaseInfoAPIResult.getData().getXB());
                                mBinding.txtArea.setText(rPersonalBaseInfoAPIResult.getData().getQY_SH()+
                                        "-"+rPersonalBaseInfoAPIResult.getData().getQY_S()+
                                        "-"+rPersonalBaseInfoAPIResult.getData().getQY_X());
                                mBinding.edtDetailaddress.setText(rPersonalBaseInfoAPIResult.getData().getXXDZ());
                                mBinding.txtNation.setText(rPersonalBaseInfoAPIResult.getData().getMZ());
                                mBinding.edtHukousuozaidi.setText(rPersonalBaseInfoAPIResult.getData().getHKSZD());
                                mBinding.edtOccupation.setText(rPersonalBaseInfoAPIResult.getData().getZY());
                                mBinding.edtWorkUnit.setText(rPersonalBaseInfoAPIResult.getData().getGZDW());
                                mBinding.edtFixdeAddress.setText(rPersonalBaseInfoAPIResult.getData().getGDDZ());
                                mBinding.edtMailingAddress.setText(rPersonalBaseInfoAPIResult.getData().getTXDZ());
                                mBinding.edtZipCode.setText(rPersonalBaseInfoAPIResult.getData().getYB());
                                mBinding.edtEmail.setText(rPersonalBaseInfoAPIResult.getData().getDZYX());
                                qy_sh=rPersonalBaseInfoAPIResult.getData().getQY_SH();
                                qy_shdm=rPersonalBaseInfoAPIResult.getData().getQY_SHDM();
                                qy_s=rPersonalBaseInfoAPIResult.getData().getQY_S();
                                qy_sdm=rPersonalBaseInfoAPIResult.getData().getQY_SDM();
                                qy_x=rPersonalBaseInfoAPIResult.getData().getQY_X();
                                qy_xdm=rPersonalBaseInfoAPIResult.getData().getQY_XDM();
                            }

                            @Override
                            public void onMock(APIResult<RPersonalBaseInfo> rPersonalBaseInfoAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
}

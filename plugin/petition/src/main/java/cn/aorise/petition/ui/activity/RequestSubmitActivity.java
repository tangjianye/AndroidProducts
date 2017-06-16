package cn.aorise.petition.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
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
import cn.aorise.petition.databinding.PetitionActivityRequestSubmitBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TSubmit;
import cn.aorise.petition.module.network.entity.response.RQuestion;
import cn.aorise.petition.module.network.entity.response.Rsubmit;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import cn.aorise.petition.ui.bean.Petition_contact_people;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/25.
 */

public class RequestSubmitActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityRequestSubmitBinding mbinding;
    private SharedPreferences sp,sp1;
    private SharedPreferences.Editor editor;
    private List<Petition_contact_people> list;
    private static final String TAG = AboutActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setColor(this, R.color.petition_status_color);
        System.out.println("法院是否受理"+sp.getString(getString(R.string.petition_sharepre_FYSFSL),""));
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_people==id) {
            Intent intent=new Intent(RequestSubmitActivity.this,RequestContactListActivity.class);
            intent.putExtra(getString(R.string.petition_activity_to_activity_number),"2");
            startActivity(intent);
        } else if (R.id.rl_content==id) {
            Intent intent=new Intent(RequestSubmitActivity.this,RequestFillContentActivity.class);
            intent.putExtra(getString(R.string.petition_activity_to_activity_number),"2");
            startActivity(intent);
        } else if (R.id.rl_adjunct==id) {
            openActivity(RequestAddAdjunctActivity.class);
        } else if (R.id.rl_submit==id) {

            submitRequest();
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
        mbinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_request_submit);
        mbinding.rlPeople.setOnClickListener(this);
        mbinding.rlContent.setOnClickListener(this);
        mbinding.rlAdjunct.setOnClickListener(this);
        mbinding.rlSubmit.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mbinding.txtContent.setText(sp.getString(getString(R.string.petition_shareprefers_request_content),""));
        if (!"".equals(sp.getString(getString(R.string.petition_shareprefers_save_list),""))){
            list = GsonUtil.fromJson(sp.getString(getString(R.string.petition_shareprefers_save_list), ""),
                    new TypeToken<List<Petition_contact_people>>() {
                    }.getType());
            mbinding.txtNum.setText(list.size()+"人");
        }
    }
    private String makerequest(){
        TSubmit tSubmit=new TSubmit();
        tSubmit.setXFR(sp1.getString(getString(R.string.petition_shareprefers_XFR_XM),""));
        tSubmit.setZJHM(sp1.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        tSubmit.setSFD_SHDM(sp.getString(getString(R.string.petition_sharepre_SFD_SHDM),""));
        tSubmit.setSFD_SH(sp.getString(getString(R.string.petition_sharepre_SFD_SH),""));
        tSubmit.setSFD_SDM(sp.getString(getString(R.string.petition_sharepre_SFD_SDM),""));
        tSubmit.setSFD_S(sp.getString(getString(R.string.petition_sharepre_SFD_S),""));
        tSubmit.setSFD_XDM(sp.getString(getString(R.string.petition_sharepre_SFD_XDM),""));
        tSubmit.setSFD_X(sp.getString(getString(R.string.petition_sharepre_SFD_X),""));
        tSubmit.setLXY(sp.getString(getString(R.string.petition_sharepre_LXY),""));
        tSubmit.setLXYDM(sp.getString(getString(R.string.petition_sharepre_LXYDM),""));
        tSubmit.setLXE(sp.getString(getString(R.string.petition_sharepre_LXE),""));
        tSubmit.setLXEDM(sp.getString(getString(R.string.petition_shareprefer_LXEDM),""));
        tSubmit.setLXS(sp.getString(getString(R.string.petition_sharepre_LXS),""));
        tSubmit.setLXSDM(sp.getString(getString(R.string.petition_shareprefer_LXSDM),""));
        tSubmit.setSFFH(sp.getString(getString(R.string.petition_sharepre_SFFH),""));
        tSubmit.setFYSFSL(sp.getString(getString(R.string.petition_sharepre_FYSFSL),""));
        tSubmit.setSFXZFY(sp.getString(getString(R.string.petition_sharepre_SFXZFY),""));
        tSubmit.setXZJGSFSL(sp.getString(getString(R.string.petition_sharepre_XZJGSFSL),""));
        tSubmit.setSFGK(sp.getString(getString(R.string.petition_sharepre_SFGK),""));
        tSubmit.setNR(sp.getString(getString(R.string.petition_shareprefers_request_content),""));
        tSubmit.setFJ(sp.getString(getString(R.string.petition_shareprefers_file_id),""));
        tSubmit.setPeoplAll(list);

        return GsonUtil.toJson(tSubmit);
    }
    private void submitRequest(){
        makerequest();
        System.out.println(makerequest());
        Subscription subscription = PetitionApiService.Factory.create().submitRequest(makerequest())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<Rsubmit>>() {
                        }.getType(), new APICallback<APIResult<Rsubmit>>() {
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
                            public void onNext(APIResult<Rsubmit> rsubmitAPIResult) {
                                if (rsubmitAPIResult.getMsg().equals(getString(R.string.petition_add_succeed))) {
                                    showToast(rsubmitAPIResult.getMsg());
                                    RequestActivity.instance.finish();
                                    RequestSubmitActivity.this.finish();
                                }else {
                                    showToast(R.string.petition_add_defealt);
                                }
                            }

                            @Override
                            public void onMock(APIResult<Rsubmit> rsubmitAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

}

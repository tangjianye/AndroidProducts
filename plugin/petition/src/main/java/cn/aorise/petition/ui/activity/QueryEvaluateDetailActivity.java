package cn.aorise.petition.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionActivityQueryEvaluationDetailBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;

import cn.aorise.petition.module.network.entity.request.TQueryEvaluateDetail;
import cn.aorise.petition.module.network.entity.response.RQueryEvaluate;
import cn.aorise.petition.module.network.entity.response.RQueryEvaluateDetail;
import cn.aorise.petition.ui.adapter.QueryEvaluateListAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import cn.aorise.petition.ui.bean.Petition_contact_people;
import cn.aorise.petition.ui.bean.QueryEvaluateListInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/2.
 */

public class QueryEvaluateDetailActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityQueryEvaluationDetailBinding mBinding;
    private RQueryEvaluateDetail rQueryEvaluateDetail=new RQueryEvaluateDetail();
    private static final String TAG = AboutActivity.class.getSimpleName();
    private List<QueryEvaluateListInfo> data=new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private List<Petition_contact_people> peoples=new ArrayList<>();
    private String adjunctList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getQueryValuate(getIntent().getStringExtra("BH"));
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_left==id) {
            mBinding.rlLeft.setBackgroundResource(R.drawable.petition_left_02);
            mBinding.txtLeft.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlMiddle.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlRight.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtRight.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.llPetitionLetterDetail.setVisibility(View.VISIBLE);
            mBinding.llOperationInfo.setVisibility(View.GONE);
            mBinding.llEvaluationDetail.setVisibility(View.GONE);
        } else if (R.id.rl_middle==id) {
            mBinding.rlLeft.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtLeft.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle.setBackgroundResource(R.drawable.petition_middle_02);
            mBinding.txtMiddle.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlRight.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtRight.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.llPetitionLetterDetail.setVisibility(View.GONE);
            mBinding.llOperationInfo.setVisibility(View.VISIBLE);
            mBinding.llEvaluationDetail.setVisibility(View.GONE);
        } else if (R.id.rl_right==id) {
            mBinding.rlLeft.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtLeft.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlRight.setBackgroundResource(R.drawable.petition_right_02);
            mBinding.txtRight.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.llPetitionLetterDetail.setVisibility(View.GONE);
            mBinding.llOperationInfo.setVisibility(View.GONE);
            mBinding.llEvaluationDetail.setVisibility(View.VISIBLE);
        } else if(R.id.rl_contact_people==id) {
            Intent intent=new Intent(QueryEvaluateDetailActivity.this,RequestContactListActivity.class);
            editor.putString(getString(R.string.petition_shareprefers_save_list),GsonUtil.toJson(peoples));
            editor.commit();
            intent.putExtra(getString(R.string.petition_activity_to_activity_number),"1");
            startActivity(intent);
        } else if (R.id.rl_content==id) {
            Intent intent=new Intent(QueryEvaluateDetailActivity.this,RequestFillContentActivity.class);
            intent.putExtra(getString(R.string.petition_activity_to_activity_number),"1");
            startActivity(intent);
        } else if (R.id.rl_adjunct==id) {

            Intent intent=new Intent(QueryEvaluateDetailActivity.this,QueryEvaluateDetailAdjunctActivity.class);
            intent.putExtra("fj",adjunctList);
            startActivity(intent);
        }
    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        editor=sp.edit();
        QueryEvaluateListInfo info=new QueryEvaluateListInfo();
        info.setJBJG(getString(R.string.petition_JBJG));
        info.setJBRY(getString(R.string.petition_JBRY));
        info.setCZSJ(getString(R.string.petition_BLSJ));
        info.setBLSC(getString(R.string.petition_BLSX));
        info.setCZLX(getString(R.string.petition_CZLX));
        data.add(info);
    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this,R.layout.petition_activity_query_evaluation_detail);
        mBinding.rlLeft.setOnClickListener(this);
        mBinding.rlMiddle.setOnClickListener(this);
        mBinding.rlRight.setOnClickListener(this);
        mBinding.rlContactPeople.setOnClickListener(this);
        mBinding.rlContent.setOnClickListener(this);
        mBinding.rlAdjunct.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    private void getQueryValuate(String BH){
        final TQueryEvaluateDetail tQueryEvaluateDetail=new TQueryEvaluateDetail();
        tQueryEvaluateDetail.setBH(BH);

        Subscription subscription = PetitionApiService.Factory.create().
                GetQueryEvaluateDetail(GsonUtil.toJson(tQueryEvaluateDetail))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<RQueryEvaluateDetail>>() {
                        }.getType(), new APICallback<APIResult<RQueryEvaluateDetail>>() {
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
                            public void onNext(APIResult<RQueryEvaluateDetail> rQueryEvaluateDetailAPIResult) {
                                rQueryEvaluateDetail=rQueryEvaluateDetailAPIResult.getData();
                                System.out.println(rQueryEvaluateDetail.getSFD());
                                if (rQueryEvaluateDetailAPIResult.getData().getFJ()==null){
                                    adjunctList="";
                                } else {
                                    adjunctList = rQueryEvaluateDetailAPIResult.getData().getFJ();
                                }
                                System.out.println("adjunct:"+adjunctList);
                                setView();
                            }

                            @Override
                            public void onMock(APIResult<RQueryEvaluateDetail> rQueryEvaluateDetailAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

    private void setView(){
        mBinding.txtAddress.setText(rQueryEvaluateDetail.getSFD());
        mBinding.txtQuestType.setText(rQueryEvaluateDetail.getWTLX());
        if (rQueryEvaluateDetail.getSFFH().equals("1")){
            mBinding.txtSFFH.setText("是");
        } else {
            mBinding.txtSFFH.setText("否");
        }
        if (rQueryEvaluateDetail.getFYSFSL().equals("1")){
            mBinding.txtFYSFSL.setText("是");
        }else {
            mBinding.txtFYSFSL.setText("否");
        }
        if (rQueryEvaluateDetail.getSFXZFY().equals("1")){
            mBinding.txtXZSFFY.setText("是");
        }else {
            mBinding.txtXZSFFY.setText("否");
        }if (rQueryEvaluateDetail.getZCJGSFSL().equals("1")){
            mBinding.txtZCJGSFSL.setText("是");
        }else {
            mBinding.txtZCJGSFSL.setText("否");
        }if (rQueryEvaluateDetail.getSFGK().equals("1")){
            mBinding.txtSFGK.setText("是");
        } else {
            mBinding.txtSFGK.setText("否");
        }
        System.out.println(rQueryEvaluateDetail.getPeopleAll());
        if (rQueryEvaluateDetail.getPeopleAll()==null){
            mBinding.txtContactNumber.setText("0人");
        }else {
            mBinding.txtContactNumber.setText(rQueryEvaluateDetail.getPeopleAll().size() + "人");
        }
        mBinding.txtContent.setText(rQueryEvaluateDetail.getNR());
        mBinding.txtEvaluateType.setText(rQueryEvaluateDetail.getPingjia().getBMJG());
        mBinding.txtEvaluateContent.setText(rQueryEvaluateDetail.getPingjia().getBMBZ());
        mBinding.txtEvaluateType1.setText(rQueryEvaluateDetail.getPingjia().getSXJG());
        mBinding.txtEvaluateContent1.setText(rQueryEvaluateDetail.getPingjia().getSXBZ());
        for (int i=0;i<rQueryEvaluateDetail.getOpretion().size();i++) {
            data.add(rQueryEvaluateDetail.getOpretion().get(i));
        }
        QueryEvaluateListAdapter queryEvaluateListAdapter=new QueryEvaluateListAdapter(data,this);
        queryEvaluateListAdapter.notifyDataSetChanged();
        mBinding.listView.setAdapter(queryEvaluateListAdapter);
        for (int i=0;i<rQueryEvaluateDetail.getPeopleAll().size();i++) {
            Petition_contact_people people=new Petition_contact_people();
            people.setName(rQueryEvaluateDetail.getPeopleAll().get(i).getXM());
            people.setNum(rQueryEvaluateDetail.getPeopleAll().get(i).getZJHM());
            people.setAddress(rQueryEvaluateDetail.getPeopleAll().get(i).getDZ());
            peoples.add(people);
        }
        editor.putString(getString(R.string.petition_shareprefers_request_content),rQueryEvaluateDetail.getNR());
        editor.commit();

    }

}

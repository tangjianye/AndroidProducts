package cn.aorise.petition.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.common.commenrefresh.LoadMoreListView;
import cn.aorise.petition.databinding.PetitionActivityQueryEvaluateBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TQueryEvaluate;
import cn.aorise.petition.module.network.entity.request.TSuggestCollect;
import cn.aorise.petition.module.network.entity.response.RQueryEvaluate;
import cn.aorise.petition.module.network.entity.response.RSuggestCollect;
import cn.aorise.petition.ui.adapter.QueryEvaluationAdapter;
import cn.aorise.petition.ui.adapter.SuggestCollectAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import cn.aorise.petition.ui.bean.QueryEvaluationInfo;
import cn.aorise.petition.ui.bean.SuggestCollectInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/2.
 */

public class QueryEvaluateActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityQueryEvaluateBinding mBinding;
    private List<RQueryEvaluate> mList=new ArrayList<>();
    private QueryEvaluationAdapter mAdapter;
    private static final String TAG = AboutActivity.class.getSimpleName();
    private int pagetemp=1,pagesize=20;
    private int ZT=1;//1为已评价，0为未评价
    private SharedPreferences sp,sp1;
    private SharedPreferences.Editor editor;
    public static Activity instance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getEvaluate(pagetemp,pagesize,ZT);
        instance=this;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_has_answer==id) {
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_02);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));

            pagetemp=1;
            ZT=1;
            getEvaluate(pagetemp,pagesize,ZT);
        } else if (R.id.rl_not_answer==id) {
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_02);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));

            pagetemp=1;
            ZT=0;
            getEvaluate(pagetemp,pagesize,ZT);
        }
    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        sp1=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        editor=sp.edit();
        mAdapter=new QueryEvaluationAdapter(mList,this);
        getEvaluate(1,pagesize,1);
    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_query_evaluate);
        mBinding.rlHasAnswer.setOnClickListener(this);
        mBinding.rlNotAnswer.setOnClickListener(this);
        mBinding.listView.setRefreshAndLoadMoreView(mBinding.refresh);
        mBinding.listView.setAdapter(mAdapter);
        mBinding.listView.onLoadComplete();//结束下拉加载更多转动条

        mBinding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pagetemp=1;
                getEvaluate(1,pagesize,ZT);
            }
        });

        mBinding.listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getEvaluate(1,pagesize*pagetemp,ZT);
                mBinding.refresh.setRefreshing(true);
            }
        });


    }

    @Override
    protected void initEvent() {
        mBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(QueryEvaluateActivity.this,QueryEvaluateDetailActivity.class);
                intent.putExtra("BH",mList.get(position).getBH());
                System.out.println(mList.get(position).getBH());
                startActivity(intent);
            }
        });

    }


    private void getEvaluate(int page,int pagesize,int ZT){
        TQueryEvaluate tQueryEvaluate=new TQueryEvaluate();
        tQueryEvaluate.setCurrentPage(page);
        tQueryEvaluate.setPageSize(pagesize);
        tQueryEvaluate.setZT(ZT);
        tQueryEvaluate.setZJHM(sp1.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        System.out.println(GsonUtil.toJson(tQueryEvaluate));
        Subscription subscription = PetitionApiService.Factory.create().
                GetQueryEvaluate(GsonUtil.toJson(tQueryEvaluate))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<List<RQueryEvaluate>>>() {
                        }.getType(), new APICallback<APIResult<List<RQueryEvaluate>>>() {
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
                            public void onNext(APIResult<List<RQueryEvaluate>> listAPIResult) {
                                System.out.println(listAPIResult);
                                mList.clear();
                                for (int i=0;i<listAPIResult.getData().size();i++){
                                    mList.add(listAPIResult.getData().get(i));
                                }
                                mAdapter.notifyDataSetChanged();
                                pagetemp++;
                                mBinding.refresh.setRefreshing(false);
                                mBinding.listView.onLoadComplete();
                            }

                            @Override
                            public void onMock(APIResult<List<RQueryEvaluate>> listAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear().commit();
    }
}

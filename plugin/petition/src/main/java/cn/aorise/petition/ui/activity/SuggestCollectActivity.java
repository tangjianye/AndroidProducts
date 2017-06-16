package cn.aorise.petition.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import cn.aorise.petition.common.commenrefresh.LoadMoreListView;
import cn.aorise.petition.databinding.PetitionActivitySuggestCollectBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TSuggestCollect;
import cn.aorise.petition.module.network.entity.response.RSuggestCollect;
import cn.aorise.petition.ui.adapter.SuggestCollectAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/28.
 */

public class SuggestCollectActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivitySuggestCollectBinding mBinding;
    private List<RSuggestCollect> mList=new ArrayList<>();
    private SuggestCollectAdapter mAdapter;
    private static final String TAG = AboutActivity.class.getSimpleName();
    private int tempPageIndex=1,pagesize=10;
    private int ZT=1;//已回复状态为1，未回复状态为0；
    private SharedPreferences sp;
    public static Activity instance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_has_answer==id) {
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_02);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));//设置点击按钮颜色变化

            tempPageIndex=1;
            ZT=1;
            getSuggestCollect(tempPageIndex,pagesize,ZT);

        } else if (R.id.rl_not_answer==id) {
            mBinding.rlHasAnswer.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtHasAnswer.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlNotAnswer.setBackgroundResource(R.drawable.petition_right_02);
            mBinding.txtNotAnswer.setTextColor(this.getResources().getColor(R.color.petition_status_color));

            tempPageIndex=1;
            ZT=0;
            getSuggestCollect(tempPageIndex,pagesize,ZT);
        }
    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        mAdapter=new SuggestCollectAdapter(mList,this);
        getSuggestCollect(1,pagesize,1);

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_suggest_collect);
        setPetition_title(this,getString(R.string.petition_suggest_collect_list_name),
                getString(R.string.petition_add_new_name),SuggestCollectAddNewActivity.class);

        mBinding.rlHasAnswer.setOnClickListener(this);
        mBinding.rlNotAnswer.setOnClickListener(this);
        //初始化refresh listview
        //mBinding.refresh.setLoadMoreListView(mBinding.listView);
        mBinding.listView.setRefreshAndLoadMoreView(mBinding.refresh);
        mBinding.listView.setAdapter(mAdapter);
        mBinding.listView.onLoadComplete();//结束下拉加载更多转动条

        mBinding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tempPageIndex=1;
                getSuggestCollect(1,pagesize,ZT);
            }
        });

        mBinding.listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getSuggestCollect(1,pagesize*tempPageIndex,ZT);
                mBinding.refresh.setRefreshing(true);
            }
        });


    }

    @Override
    protected void initEvent() {

    }


    private void getSuggestCollect(int page,int pagesize,int ZT){
        TSuggestCollect tSuggestCollect=new TSuggestCollect();
        tSuggestCollect.setCurrentPage(page);
        tSuggestCollect.setPageSize(pagesize);
        tSuggestCollect.setZT(ZT);
        tSuggestCollect.setZJHM(sp.getString(getString(R.string.petition_shareprefers_XFR_ZJHM),""));
        System.out.println(GsonUtil.toJson(tSuggestCollect));
        Subscription subscription = PetitionApiService.Factory.create().
                getSuggestCollect(GsonUtil.toJson(tSuggestCollect))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<List<RSuggestCollect>>>() {
                        }.getType(), new APICallback<APIResult<List<RSuggestCollect>>>() {
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
                            public void onNext(APIResult<List<RSuggestCollect>> listAPIResult) {
                                System.out.println(listAPIResult);
                                mList.clear();
                                for (int i=0;i<listAPIResult.getData().size();i++){
                                    mList.add(listAPIResult.getData().get(i));
                                }
                                mAdapter.notifyDataSetChanged();
                                tempPageIndex++;
                                mBinding.refresh.setRefreshing(false);
                                mBinding.listView.onLoadComplete();
                            }

                            @Override
                            public void onMock(APIResult<List<RSuggestCollect>> listAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

}

package cn.aorise.petition.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import cn.aorise.petition.databinding.PetitionActivityGuideRulesBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TGuideRules;
import cn.aorise.petition.module.network.entity.response.RGuideRules;
import cn.aorise.petition.module.network.entity.response.RSuggestCollect;
import cn.aorise.petition.ui.adapter.GuideRulesAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;

/**
 * Created by Administrator on 2017/5/15.
 */

public class GuideRulesActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityGuideRulesBinding mBinding;
    private List<RGuideRules> mList=new ArrayList<>();
    private GuideRulesAdapter mAdapter;
    private static final String TAG = AboutActivity.class.getSimpleName();
    private String GGLX="5";
    private String CXTJ="";
    private int currentpage=1;
    private int pageSize=20;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_petition_return==id) {
            GuideRulesActivity.this.finish();
        } else if (R.id.rl_petition_right==id) {
            mBinding.rlSearch.setVisibility(View.VISIBLE);
            mBinding.llToolbar.setVisibility(GONE);
        } else if (R.id.rl_petition_return==id) {
            GuideRulesActivity.this.finish();
        } else if (R.id.rl_cancle==id) {
            mBinding.edtSearch.setText("");
            mBinding.rlSearch.setVisibility(GONE);
            mBinding.llToolbar.setVisibility(View.VISIBLE);
        } else if (R.id.img_clear==id) {
            mBinding.edtSearch.setText("");

        }
    }

    @Override
    protected void initData() {
        mAdapter=new GuideRulesAdapter(mList,this);
        GetGuideRules(GGLX,CXTJ,currentpage,pageSize);
    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_guide_rules);
        mBinding.rlPetitionReturn.setOnClickListener(this);
        mBinding.rlPetitionRight.setOnClickListener(this);
        mBinding.rlCancle.setOnClickListener(this);
        mBinding.imgClear.setOnClickListener(this);
        SearchListener();
        //初始化refresh listview
        //mBinding.refresh.setLoadMoreListView(mBinding.listView);
        mBinding.listView.setRefreshAndLoadMoreView(mBinding.refresh);
        mBinding.listView.setAdapter(mAdapter);
        mBinding.listView.onLoadComplete();//结束下拉加载更多转动条

        mBinding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentpage=1;
                GetGuideRules(GGLX,CXTJ,currentpage,pageSize);
            }
        });

        mBinding.listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                GetGuideRules(GGLX,CXTJ,1,pageSize*currentpage);
                mBinding.refresh.setRefreshing(true);
            }
        });
        mBinding.edtSearch.setOnKeyListener(new View.OnKeyListener() {//输入完后按键盘上的搜索键【回车键改为了搜索键】

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER){//修改回车键功能
        // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    GuideRulesActivity.this
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
        //跳转到搜索结果界面
                    mBinding.llToolbar.setVisibility(View.VISIBLE);
                    mBinding.rlSearch.setVisibility(GONE);

                }
                return false;
            }
        });
    }

    @Override
    protected void initEvent() {

    }
    private void GetGuideRules(String GGLX,String CXTJ,int currentPage,int pageSize){
        TGuideRules tGuideRules=new TGuideRules();
        tGuideRules.setGGLX(GGLX);
        tGuideRules.setCXTJ(CXTJ);
        tGuideRules.setCurrentPage(currentPage);
        tGuideRules.setPageSize(pageSize);
        System.out.println(GsonUtil.toJson(tGuideRules));
        Subscription subscription = PetitionApiService.Factory.create().
                GetGuideRules(GsonUtil.toJson(tGuideRules))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<RGuideRules>>() {
                        }.getType(), new APICallback<APIResult<List<RGuideRules>>>() {
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
                            public void onNext(APIResult<List<RGuideRules>> listAPIResult) {
                                mList.clear();
                                for (int i=0;i<listAPIResult.getData().size();i++){
                                    mList.add(listAPIResult.getData().get(i));
                                }
                                mAdapter.notifyDataSetChanged();
                                currentpage++;
                                mBinding.refresh.setRefreshing(false);
                                mBinding.listView.onLoadComplete();

                            }

                            @Override
                            public void onMock(APIResult<List<RGuideRules>> listAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

    private void SearchListener(){
        mBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                GetGuideRules(GGLX,s.toString(),1,pageSize);
            }
        });
        mBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(GuideRulesActivity.this,GuideRulesDetailActivity.class);
                intent.putExtra("BH",mList.get(position).getBH());
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });
    }

}

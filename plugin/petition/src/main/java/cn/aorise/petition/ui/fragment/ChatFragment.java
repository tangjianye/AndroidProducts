package cn.aorise.petition.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.common.commenrefresh.LoadMoreListView;
import cn.aorise.petition.databinding.PetitionFragmentChatBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TGuideRules;
import cn.aorise.petition.module.network.entity.response.RGuideRules;
import cn.aorise.petition.ui.activity.AboutActivity;
import cn.aorise.petition.ui.activity.ContactsActivity;
import cn.aorise.petition.ui.activity.GuideRulesActivity;
import cn.aorise.petition.ui.activity.GuideRulesDetailActivity;
import cn.aorise.petition.ui.adapter.GuideRulesAdapter;
import cn.aorise.petition.ui.base.PetitionBaseFragment;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2017/3/2.
 */
public class ChatFragment extends PetitionBaseFragment implements View.OnClickListener{
    private PetitionFragmentChatBinding mBinding;
    private String GGLX="1";
    private String CXTJ="";
    private int currentpage=1;
    private int pageSize=20;
    private List<RGuideRules> mList=new ArrayList<>();
    private GuideRulesAdapter mAdapter;
    private static final String TAG = AboutActivity.class.getSimpleName();
    public ChatFragment() {
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.petition_fragment_chat, container, false);
        mAdapter=new GuideRulesAdapter(mList,getActivity());
        GetGuideRules("1","",1,pageSize);
        mBinding.rlLeft.setOnClickListener(this);
        mBinding.rlMiddle.setOnClickListener(this);
        mBinding.rlMiddle01.setOnClickListener(this);
        mBinding.rlRight.setOnClickListener(this);
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

        mBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),GuideRulesDetailActivity.class);
                intent.putExtra("BH",mList.get(position).getBH());
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.petition_menu_chat, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_contract == item.getItemId()) {
            getBaseActivity().openActivity(ContactsActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_left==id) {
            mBinding.rlLeft.setBackgroundResource(R.drawable.petition_left_02);
            mBinding.txtLeft.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlMiddle.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle01.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle01.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlRight.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtRight.setTextColor(this.getResources().getColor(R.color.petition_ffffff));

            GGLX="1";
            currentpage=1;
            GetGuideRules(GGLX,CXTJ,1,pageSize*currentpage);

        } else if (R.id.rl_middle==id) {
            mBinding.rlLeft.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtLeft.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle.setBackgroundResource(R.drawable.petition_middle_02);
            mBinding.txtMiddle.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlMiddle01.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle01.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlRight.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtRight.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            GGLX="2";
            currentpage=1;
            GetGuideRules(GGLX,CXTJ,1,pageSize*currentpage);
        } else if (R.id.rl_middle_01==id) {
            mBinding.rlLeft.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtLeft.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle01.setBackgroundResource(R.drawable.petition_middle_02);
            mBinding.txtMiddle01.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            mBinding.rlRight.setBackgroundResource(R.drawable.petition_right_01);
            mBinding.txtRight.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            GGLX="3";
            currentpage=1;
            GetGuideRules(GGLX,CXTJ,1,pageSize*currentpage);
        } else if (R.id.rl_right==id) {
            mBinding.rlLeft.setBackgroundResource(R.drawable.petition_left_01);
            mBinding.txtLeft.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlMiddle01.setBackgroundResource(R.drawable.petition_middle_01);
            mBinding.txtMiddle01.setTextColor(this.getResources().getColor(R.color.petition_ffffff));
            mBinding.rlRight.setBackgroundResource(R.drawable.petition_right_02);
            mBinding.txtRight.setTextColor(this.getResources().getColor(R.color.petition_status_color));
            GGLX="4";
            currentpage=1;
            GetGuideRules(GGLX,CXTJ,1,pageSize*currentpage);
        }
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
                .subscribe(Utils.mockSubscriber((BaseActivity) getActivity(), Mock.PETITION_QUESTION_TYPE,
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

}

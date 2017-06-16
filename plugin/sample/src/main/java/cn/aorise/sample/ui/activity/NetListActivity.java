package cn.aorise.sample.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.ui.view.recyclerview.LoadMoreListener;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.common.databinding.AoriseActivityBaseListBinding;
import cn.aorise.sample.R;
import cn.aorise.sample.common.Utils;
import cn.aorise.sample.module.network.SampleApiService;
import cn.aorise.sample.module.network.Mock;
import cn.aorise.sample.module.network.entity.response.Hospital;
import cn.aorise.sample.ui.adapter.BindingHospitalAdapter;
import cn.aorise.sample.ui.base.SampleBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Deprecated
public class NetListActivity extends SampleBaseActivity implements SwipeRefreshLayout.OnRefreshListener
        , LoadMoreListener.OnLoadMoreListener {
    private static final String TAG = NetListActivity.class.getSimpleName();
    private static final String REFRESH = "swipeRefresh";
    private static final String LOAD_MORE = "load_more";
    private LoadMoreListener mLoadMoreListener;
    private AoriseActivityBaseListBinding mBinding;
    private BindingHospitalAdapter mAdapter;
    private List<Hospital> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // HandlerUtil.HANDLER.removeCallbacksAndMessages(null);
        RxAPIManager.getInstance().cancel(REFRESH);
        RxAPIManager.getInstance().cancel(LOAD_MORE);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mAdapter = new BindingHospitalAdapter(this, mList);

        mLoadMoreListener = new LoadMoreListener();
        mLoadMoreListener.setLoadMoreListener(this);
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.aorise_activity_base_list);
        mBinding.swipeRefresh.setOnRefreshListener(this);

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.addOnScrollListener(mLoadMoreListener);
    }

    @Override
    protected void initEvent() {
        refresh();
    }

    @Override
    public void showLoadingDialog() {
        // 屏蔽默认的进度条
        // super.showLoadingDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        // 屏蔽默认的进度条
        // super.dismissLoadingDialog();
    }

    @Override
    public void onRefresh() {
        AoriseLog.i(TAG, "onRefresh");
        // mLoadMoreListener.setLoadingMoreEnabled(false);
        refresh();
    }


    @Override
    public void onLoadMore() {
        loadMore();
    }

    /**
     * refresh
     **********************************************************************************************/
    private void refresh() {
        mLoadMoreListener.onLoadMoreComplete();
        RxAPIManager.getInstance().cancel(LOAD_MORE);

        Subscription subscription = SampleApiService.Factory.create().getHospital()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.GET_ALL_HOSPITAL,
                        new TypeToken<APIResult<List<Hospital>>>() {
                        }.getType(),
                        new APICallback<APIResult<List<Hospital>>>() {
                            @Override
                            public void onStart() {
                                swipeRefresh();
                            }

                            @Override
                            public void onError(Throwable e) {
                                mBinding.swipeRefresh.setRefreshing(false);
                                // mLoadMoreListener.onLoadMoreComplete();
                            }

                            @Override
                            public void onCompleted() {
                                mBinding.swipeRefresh.setRefreshing(false);
                                // mLoadMoreListener.onLoadMoreComplete();
                            }

                            @Override
                            public void onNext(APIResult<List<Hospital>> hospitalAPIResult) {
                                handleRefresh(hospitalAPIResult);
                            }

                            @Override
                            public void onMock(APIResult<List<Hospital>> hospitalAPIResult) {
                                handleRefresh(hospitalAPIResult);
                            }
                        }
                ));
        RxAPIManager.getInstance().add(REFRESH, subscription);
    }

    private void handleRefresh(APIResult<List<Hospital>> hospitalAPIResult) {
        mBinding.swipeRefresh.setRefreshing(false);
        // mLoadMoreListener.onLoadMoreComplete();

        if (hospitalAPIResult.getData() != null && hospitalAPIResult.getData().size() > 0) {
            success(hospitalAPIResult);
        } else {
            empty();
        }
    }

    private void success(APIResult<List<Hospital>> hospitalAPIResult) {
        mList.clear();
        mList.addAll(hospitalAPIResult.getData());
        mAdapter.setList(mList);
    }

    private void empty() {
        mAdapter.addEmptyView(AoriseUtil.inflateEmptyView(this, mBinding.recyclerView));
        // mAdapter.notifyDataSetChanged();
    }

    /**
     * 刷新
     */
    private void swipeRefresh() {
        mBinding.swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                AoriseLog.i(TAG, "swipeRefresh");
                mBinding.swipeRefresh.setRefreshing(true);
            }
        });
    }

    /**
     * loadMore
     **********************************************************************************************/
    private void loadMore() {
        mBinding.swipeRefresh.setRefreshing(false);
        RxAPIManager.getInstance().cancel(REFRESH);

        Subscription subscription = SampleApiService.Factory.create().getHospital()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.GET_ALL_HOSPITAL,
                        new TypeToken<APIResult<List<Hospital>>>() {
                        }.getType(),
                        new APICallback<APIResult<List<Hospital>>>() {
                            @Override
                            public void onStart() {
                                mAdapter.addFooterView(AoriseUtil.inflateFooterView(NetListActivity.this, mBinding.recyclerView));
                            }

                            @Override
                            public void onError(Throwable e) {
                                mLoadMoreListener.onLoadMoreComplete();
                            }

                            @Override
                            public void onCompleted() {
                                mLoadMoreListener.onLoadMoreComplete();
                            }

                            @Override
                            public void onNext(APIResult<List<Hospital>> hospitalAPIResult) {
                                handleLoadMore(hospitalAPIResult);
                            }

                            @Override
                            public void onMock(APIResult<List<Hospital>> hospitalAPIResult) {
                                handleLoadMore(hospitalAPIResult);
                            }
                        }
                ));
        RxAPIManager.getInstance().add(LOAD_MORE, subscription);
    }

    private void handleLoadMore(APIResult<List<Hospital>> hospitalAPIResult) {
        mLoadMoreListener.onLoadMoreComplete();

        if (hospitalAPIResult.getData() != null && hospitalAPIResult.getData().size() > 0) {
            mList.addAll(hospitalAPIResult.getData());
            mAdapter.setList(mList);
        }
    }
}

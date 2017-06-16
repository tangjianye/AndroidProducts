package cn.aorise.common.core.ui.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import cn.aorise.common.R;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.ui.view.recyclerview.BindingRecyclerViewAdapter;
import cn.aorise.common.core.ui.view.recyclerview.LoadMoreListener;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.databinding.AoriseActivityBaseListBinding;


public abstract class BaseListActivity<K extends BindingRecyclerViewAdapter<T>, T> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener
        , LoadMoreListener.OnLoadMoreListener {
    private static final String TAG = BaseListActivity.class.getSimpleName();
    protected static final String REFRESH = "refresh";
    protected static final String LOAD_MORE = "load_more";
    protected LoadMoreListener mLoadMoreListener;
    protected AoriseActivityBaseListBinding mBinding;
    protected K mAdapter;
    protected List<T> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxAPIManager.getInstance().cancel(REFRESH);
        RxAPIManager.getInstance().cancel(LOAD_MORE);
    }

    @Override
    protected void initData() {
        AoriseLog.i(TAG, "initData");
        // mList = new ArrayList<>();
        // mAdapter = new BindingHospitalAdapter(this, mList);
        mLoadMoreListener = new LoadMoreListener();
        mLoadMoreListener.setLoadMoreListener(this);
    }

    @Override
    protected void initView() {
        AoriseLog.i(TAG, "initView");
        mBinding = DataBindingUtil.setContentView(this, R.layout.aorise_activity_base_list);
        mBinding.swipeRefresh.setOnRefreshListener(this);

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.addOnScrollListener(mLoadMoreListener);
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {
        AoriseLog.i(TAG, "initEvent");
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
        refresh();
    }


    @Override
    public void onLoadMore() {
        loadMore();
    }

    /**
     * UI刷新
     */
    protected void swipeRefresh() {
        mBinding.swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                AoriseLog.i(TAG, "swipeRefresh");
                mBinding.swipeRefresh.setRefreshing(true);
            }
        });
    }

    /**
     * 刷新数据
     */
    protected abstract void refresh();

    /**
     * 下拉加载更多数据
     */
    protected abstract void loadMore();
}

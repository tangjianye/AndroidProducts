package cn.aorise.common.core.ui.view.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.aorise.common.core.utils.assist.AoriseLog;

/**
 * Created by pc on 2017/3/15.
 */

public class LoadMoreListener extends RecyclerView.OnScrollListener {
    private static final String TAG = LoadMoreListener.class.getSimpleName();
    private BindingRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private OnLoadMoreListener mLoadMoreListener;
    /**
     * 通过滚动方向判断是否允许上拉加载
     */
    public boolean isLoadingMoreEnabled = true;
    /**
     * 判断是否加载完成
     */
    private boolean hasCompleted = true;

    public LoadMoreListener() {
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        mRecyclerView = recyclerView;
        mAdapter = (BindingRecyclerViewAdapter) recyclerView.getAdapter();
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = mAdapter.getItemCount();
        AoriseLog.i(TAG, "onScrollStateChanged lastVisibleItem = " + lastVisibleItem
                + " ;visibleItemCount =" + visibleItemCount
                + " ;totalItemCount =" + totalItemCount
                + " ;isLoadingMoreEnabled =" + isLoadingMoreEnabled);

        if (RecyclerView.SCROLL_STATE_IDLE == newState
                && visibleItemCount > 0
                && lastVisibleItem >= totalItemCount - 1
                && isLoadingMoreEnabled) {

            AoriseLog.i(TAG, "onScrollStateChanged hasCompleted = " + hasCompleted);
            if (null != mLoadMoreListener && hasCompleted) {
                AoriseLog.i(TAG, "onScrollStateChanged onLoadMore");
                hasCompleted = false;
                mLoadMoreListener.onLoadMore();
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // hasCompleted = false;
        isLoadingMoreEnabled = dy > 0;
        AoriseLog.i(TAG, "onScrolled: isLoadingMoreEnabled = " + isLoadingMoreEnabled + " ;dy = " + dy);
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    public void addFooterView(View footerView) {
        if (null != mAdapter && null != mRecyclerView) {
            mAdapter.addFooterView(footerView);
            // 下滑到底部
            mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void onLoadMoreComplete() {
        if (null != mAdapter && mAdapter.getFooterCount() > 0) {
            hasCompleted = true;
            isLoadingMoreEnabled = true;
            mAdapter.removeFooterView();
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}

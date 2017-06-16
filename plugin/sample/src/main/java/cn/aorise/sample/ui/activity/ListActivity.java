package cn.aorise.sample.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.handler.HandlerUtil;
import cn.aorise.common.databinding.AoriseActivityBaseListBinding;
import cn.aorise.sample.R;
import cn.aorise.sample.db.DbHelper;
import cn.aorise.sample.db.entity.SampleNote;
import cn.aorise.sample.ui.adapter.BindingNoteAdapter;
import cn.aorise.sample.ui.base.SampleBaseActivity;

public class ListActivity extends SampleBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ListActivity.class.getSimpleName();
    private AoriseActivityBaseListBinding mBinding;
    private BindingNoteAdapter mAdapter;
    private List<SampleNote> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HandlerUtil.HANDLER.removeCallbacksAndMessages(null);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mAdapter = new BindingNoteAdapter(this, mList);
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.aorise_activity_base_list);
        mBinding.swipeRefresh.setOnRefreshListener(this);

//        // 自动刷新
//        mBinding.swipeRefresh.post(new Runnable() {
//            @Override
//            public void run() {
//                AoriseLog.i(TAG, "自动刷新");
//                mBinding.swipeRefresh.setRefreshing(true);
//            }
//        });

//        // 自动关闭
//        HandlerUtil.runOnUiThreadDelay(new Runnable() {
//            @Override
//            public void run() {
//                mBinding.swipeRefresh.setRefreshing(false);
//                AoriseLog.i(TAG, "自动关闭");
//            }
//        }, 3000);

        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onRefresh() {
        AoriseLog.i(TAG, "onRefresh");

        HandlerUtil.runOnUiThreadDelay(new Runnable() {
            @Override
            public void run() {
                AoriseLog.i(TAG, "手动触发");
                mBinding.swipeRefresh.setRefreshing(false);

                mList.clear();
                mList.addAll(DbHelper.getInstance().getSampleNoteDao().loadAll());
                mAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }
}

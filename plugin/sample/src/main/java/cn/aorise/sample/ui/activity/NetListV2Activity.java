package cn.aorise.sample.ui.activity;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.ui.base.BaseListActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.sample.common.Utils;
import cn.aorise.sample.module.network.SampleApiService;
import cn.aorise.sample.module.network.Mock;
import cn.aorise.sample.module.network.entity.response.Hospital;
import cn.aorise.sample.ui.adapter.BindingHospitalAdapter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetListV2Activity extends BaseListActivity<BindingHospitalAdapter, Hospital> {
    private static final String TAG = NetListV2Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        super.initData();
        AoriseLog.i(TAG, "initData");
        mList = new ArrayList<>();
        mAdapter = new BindingHospitalAdapter(this, mList);
    }

    /**
     * refresh
     **********************************************************************************************/
    @Override
    protected void refresh() {
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
                            }

                            @Override
                            public void onCompleted() {
                                mBinding.swipeRefresh.setRefreshing(false);
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
    }

    /**
     * loadMore
     **********************************************************************************************/
    @Override
    protected void loadMore() {
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
                                mLoadMoreListener.addFooterView(AoriseUtil.inflateFooterView(NetListV2Activity.this, mBinding.recyclerView));
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

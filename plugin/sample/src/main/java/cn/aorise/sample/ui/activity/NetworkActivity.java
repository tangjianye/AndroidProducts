package cn.aorise.sample.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.sample.R;
import cn.aorise.sample.common.Utils;
import cn.aorise.sample.databinding.SampleActivityNetworkBinding;
import cn.aorise.sample.module.network.SampleApiService;
import cn.aorise.sample.module.network.Mock;
import cn.aorise.sample.module.network.entity.response.Douban;
import cn.aorise.sample.ui.base.SampleBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkActivity extends SampleBaseActivity implements View.OnClickListener {
    private static final String TAG = NetworkActivity.class.getSimpleName();
    private SampleActivityNetworkBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxAPIManager.getInstance().cancel(TAG);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.sample_activity_network);
        mBinding.btnGetData.setOnClickListener(this);
        mBinding.btnCancel.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (R.id.btn_get_data == id) {
            request();
        } else if (R.id.btn_cancel == id) {
            // 取消下载
            RxAPIManager.getInstance().cancel(TAG);
        } else {
            AoriseLog.i(TAG, "onClick");
        }
    }

    private void request() {
        Subscription subscription = SampleApiService.Factory.create().getDouban()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.GET_DOUBAN, Douban.class, new APICallback<Douban>() {
                    @Override
                    public void onStart() {
                        AoriseLog.i(TAG, "onStart");
                    }

                    @Override
                    public void onError(Throwable e) {
                        AoriseLog.i(TAG, "onError");
                    }

                    @Override
                    public void onCompleted() {
                        AoriseLog.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onNext(Douban douban) {
                        AoriseLog.i(TAG, "onNext");
                        success(douban);
                    }

                    @Override
                    public void onMock(Douban douban) {
                        AoriseLog.i(TAG, "onMock");
                        success(douban);
                    }
                }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }

    private void success(Douban douban) {
        mBinding.txtTitle.setText(douban.getPublisher());
        mBinding.etStream.setText(douban.toString());
    }
}

package cn.aorise.petition.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionGuideRulesDetailBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TQueryEvaluateDetail;
import cn.aorise.petition.module.network.entity.response.RGuideRulesDetail;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/16.
 */

public class GuideRulesDetailActivity extends PetitionBaseActivity implements View.OnClickListener{
    private static final String TAG = AboutActivity.class.getSimpleName();
    private PetitionGuideRulesDetailBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetGuideRulesDetail(getIntent().getStringExtra("BH"));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_guide_rules_detail);
        if (getIntent().getStringExtra("id").equals("1")){
            setToolBarTitle("信访资讯");
        }else if (getIntent().getStringExtra("id").equals("2")){

        }
    }

    @Override
    protected void initEvent() {

    }

    private void GetGuideRulesDetail (String BH) {
        TQueryEvaluateDetail tQueryEvaluateDetail=new TQueryEvaluateDetail();
        tQueryEvaluateDetail.setBH(BH);
        Subscription subscription = PetitionApiService.Factory.create().
                GetGuideRulesDetail(GsonUtil.toJson(tQueryEvaluateDetail))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_QUESTION_TYPE,
                        new TypeToken<APIResult<RGuideRulesDetail>>() {
                        }.getType(), new APICallback<APIResult<RGuideRulesDetail>>() {
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
                            public void onNext(APIResult<RGuideRulesDetail> rGuideRulesDetailAPIResult) {
                                mBinding.txtTitle.setText(rGuideRulesDetailAPIResult.getData().getBT());
                                mBinding.txtContent.setText("    "+rGuideRulesDetailAPIResult.getData().getNR());
                            }

                            @Override
                            public void onMock(APIResult<RGuideRulesDetail> rGuideRulesDetailAPIResult) {

                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
}

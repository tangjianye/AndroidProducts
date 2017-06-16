package cn.aorise.petition.ui.activity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.module.network.RxAPIManager;
import cn.aorise.common.core.ui.base.BaseWebActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.config.Constant;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.request.TLogin;
import cn.aorise.petition.module.network.entity.response.RLogin;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2015/11/26 0026.
 */
public class AboutActivity extends BaseWebActivity {
    private static final String TAG = AboutActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(Constant.ABOUT_URI);

        toLogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxAPIManager.getInstance().cancel(TAG);
    }

    private String makeRequest() {
        TLogin tLogin = new TLogin();
        tLogin.setMM("123456");
        tLogin.setYHM("13988888888");
        return GsonUtil.toJson(tLogin);
    }

    private void toLogin() {
        String request = makeRequest();

        Subscription subscription = PetitionApiService.Factory.create().peopleLogin(request)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Utils.mockSubscriber(this, Mock.PETITION_LOGIN,
                        new TypeToken<APIResult<RLogin>>() {
                        }.getType(),
                        new APICallback<APIResult<RLogin>>() {
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
                            public void onNext(APIResult<RLogin> rLoginAPIResult) {
                                AoriseLog.i(TAG, rLoginAPIResult.toString());
                            }

                            @Override
                            public void onMock(APIResult<RLogin> rLoginAPIResult) {
                                AoriseLog.i(TAG, rLoginAPIResult.toString());
                            }
                        }));
        RxAPIManager.getInstance().add(TAG, subscription);
    }
}

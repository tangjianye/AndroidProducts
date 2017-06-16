package cn.aorise.common.component.network;

import cn.aorise.common.component.network.entity.response.AccountInfo;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.utils.assist.DebugUtil;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by pc on 2017/3/8.
 */
public interface AoriseApiService {
    @GET("v2/book/1220562")
    Observable<APIResult<AccountInfo>> getLogin();

    class Factory {
        public Factory() {
        }

        public static AoriseApiService create() {
            return RetrofitFactory.getInstance().create(DebugUtil.isDebug(), AoriseApiService.class, API.BASE_URL);
        }
    }
}

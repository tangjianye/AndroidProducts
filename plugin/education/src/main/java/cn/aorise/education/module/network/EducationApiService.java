package cn.aorise.education.module.network;

import cn.aorise.common.component.network.RetrofitFactory;
import cn.aorise.common.core.utils.assist.DebugUtil;
import cn.aorise.education.module.network.entity.response.Douban;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by pc on 2017/3/8.
 */
public interface EducationApiService {
    @GET("v2/book/1220562")
    Observable<Douban> getDouban();

    class Factory {
        public Factory() {
        }

        public static EducationApiService create() {
            return RetrofitFactory.getInstance().create(DebugUtil.isDebug(), EducationApiService.class, API.BASE_URL);
        }
    }
}

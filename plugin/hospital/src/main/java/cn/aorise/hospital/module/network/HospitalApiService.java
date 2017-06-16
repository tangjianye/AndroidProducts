package cn.aorise.hospital.module.network;

import java.util.List;

import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.utils.assist.DebugUtil;
import cn.aorise.hospital.BuildConfig;
import cn.aorise.hospital.module.network.entity.response.Douban;
import cn.aorise.hospital.module.network.entity.response.Hospital;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by pc on 2017/3/8.
 */
public interface HospitalApiService {
    @GET("v2/book/1220562")
    Observable<Douban> getDouban();

    @GET("v2/book/1220562")
    Observable<APIResult<List<Hospital>>> getHospital();

    class Factory {
        public Factory() {
        }

        public static HospitalApiService create() {
            return RetrofitFactory.getInstance().create(DebugUtil.isDebug(), HospitalApiService.class, API.BASE_URL);
        }
    }
}

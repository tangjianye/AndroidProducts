package cn.aorise.petition.module.network;

import java.util.List;

import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.utils.assist.DebugUtil;
import cn.aorise.petition.module.network.entity.response.Hospital;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by pc on 2017/3/8.
 */
public interface PetitionApiService {
    @GET("v2/book/1220562")
    Observable<APIResult<List<Hospital>>> getHospital();

    class Factory {
        public Factory() {
        }

        public static PetitionApiService create() {
            return RetrofitFactory.getInstance().create(DebugUtil.isDebug(), PetitionApiService.class, API.BASE_URL);
        }
    }
}

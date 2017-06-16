package cn.aorise.petition.module.network;

import java.util.List;

import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.utils.assist.DebugUtil;
import cn.aorise.petition.module.network.entity.request.TSubmitPersonalInfo;
import cn.aorise.petition.module.network.entity.response.RAddAdviceCollect;
import cn.aorise.petition.module.network.entity.response.RGuideRules;
import cn.aorise.petition.module.network.entity.response.RGuideRulesDetail;
import cn.aorise.petition.module.network.entity.response.RLogin;
import cn.aorise.petition.module.network.entity.response.RPersonalBaseInfo;
import cn.aorise.petition.module.network.entity.response.RQueryEvaluate;
import cn.aorise.petition.module.network.entity.response.RQueryEvaluateDetail;
import cn.aorise.petition.module.network.entity.response.RQuestion;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.module.network.entity.response.RSuggestCollect;
import cn.aorise.petition.module.network.entity.response.RUpLoadFile;
import cn.aorise.petition.module.network.entity.response.Rsubmit;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by pc on 2017/3/8.
 */
public interface PetitionApiService {
    @GET("Login/peopleLogin")
    Observable<APIResult<RLogin>> peopleLogin(@Query("strRequest") String strRequest);

    @Multipart
    @POST("DictionaryInfo/fileUpload")
    Observable<APIResult<RUpLoadFile>> upDateInfoImage(@Part MultipartBody.Part file);

    @GET("DictionaryInfo/problemType")
    Observable<APIResult<List<RQuestion>>> getQuestionType(@Query("strRequest") String strRequest);

    @GET("DictionaryInfo/regionalLink")
    Observable<APIResult<List<RQuestion>>> getAddress(@Query("strRequest") String strRequest);

    @GET("PetitionLetter/complaint")
    Observable<APIResult<Rsubmit>> submitRequest(@Query("strRequest") String strRequest);

    @GET("PetitionLetter/adviceCollectQuery")
    Observable<APIResult<List<RSuggestCollect>>> getSuggestCollect(@Query("strRequest") String strRequest);

    @GET("PetitionLetter/addAdviceCollect")
    Observable<APIResult<RAddAdviceCollect>> AddAdviceCollect(@Query("strRequest") String strRequest);

    @GET("Login/peopleRegist")
    Observable<APIResult<RRegister>> Register(@Query("strRequest") String strRequest);

    @GET("PetitionLetter/queryEvaluate")
    Observable<APIResult<List<RQueryEvaluate>>> GetQueryEvaluate(@Query("strRequest") String strRequest);

    @GET("PetitionLetter/queryEvaluateAll")
    Observable<APIResult<RQueryEvaluateDetail>> GetQueryEvaluateDetail(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/verifycodeSend")
    Observable<APIResult<RRegister>> GetVerificationCode(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/verifyMessage")
    Observable<APIResult<RRegister>> VerifyCode(@Query("strRequest") String strRequest);

    @GET("Fingerpost/petitionletterRules")
    Observable<APIResult<List<RGuideRules>>> GetGuideRules(@Query("strRequest") String strRequest);

    @GET("Fingerpost/petitionRuleDetailed")
    Observable<APIResult<RGuideRulesDetail>> GetGuideRulesDetail(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/peopleSaveImfo")
    Observable<APIResult<RRegister>> SubmitPersonalInfo(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/peopleBaseImfo")
    Observable<APIResult<RPersonalBaseInfo>> GetPeopleBaseInfo(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/verifyPassword")
    Observable<APIResult<RRegister>> VerifyPassword(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/changePhoneNum")
    Observable<APIResult<RRegister>> ChangePhoneNum(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/changePassword")
    Observable<APIResult<RRegister>> ChangePassword(@Query("strRequest") String strRequest);

    @GET("ComplainantInfo/findPassword")
    Observable<APIResult<RRegister>> FoundPassword(@Query("strRequest") String strRequest);

    @Streaming //大文件时要加不然会OOM
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);


    class Factory {
        public Factory() {
        }

        public static PetitionApiService create() {
            return RetrofitFactory.getInstance().create(DebugUtil.isDebug(), PetitionApiService.class, API.BASE_URL);
        }
    }
}

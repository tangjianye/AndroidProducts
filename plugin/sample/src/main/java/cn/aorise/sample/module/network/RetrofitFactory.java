package cn.aorise.sample.module.network;

import java.util.concurrent.TimeUnit;

import cn.aorise.common.core.config.AoriseConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2017/3/8.
 */
public class RetrofitFactory {
    private static RetrofitFactory sInstance;
    private OkHttpClient.Builder mHttpBuilder;
    private Retrofit.Builder mRetrofitBuilder;
    private Retrofit mRetrofit;

    public static RetrofitFactory getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitFactory.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitFactory();
                }
            }
        }
        return sInstance;
    }

    private RetrofitFactory() {
        mHttpBuilder = new OkHttpClient().newBuilder();
        mRetrofitBuilder = new Retrofit.Builder();
    }

    public <T> T create(boolean debug, final Class<T> service, String uri) {
        if (null != mHttpBuilder && null != mRetrofitBuilder) {
            mHttpBuilder.readTimeout(AoriseConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            mHttpBuilder.connectTimeout(AoriseConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            if (debug) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                mHttpBuilder.addInterceptor(interceptor);
            }
            // mHttpBuilder.addInterceptor(new HeaderInterceptor());
            OkHttpClient client = mHttpBuilder.build();

            mRetrofit = mRetrofitBuilder.baseUrl(uri)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.newThread()))
                    .build();
        }
        return mRetrofit.create(service);
    }
}

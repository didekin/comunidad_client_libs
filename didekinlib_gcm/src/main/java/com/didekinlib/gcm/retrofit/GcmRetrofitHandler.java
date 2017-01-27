package com.didekinlib.gcm.retrofit;


import com.didekinlib.gcm.model.common.ErrorBean;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * User: pedro@didekin
 * Date: 05/08/15
 * Time: 20:07
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class GcmRetrofitHandler {

    private final Retrofit retrofit;

    public GcmRetrofitHandler(final String hostPort, int timeOut)
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(hostPort)
                .client(getOkHttpClient(timeOut))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T getService(Class<T> endPointInterface)
    {
        return retrofit.create(endPointInterface);
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }

    public ErrorBean getErrorBean(Response<?> response) throws IOException
    {
        Converter<ResponseBody, ErrorBean> converter = retrofit.responseBodyConverter(ErrorBean.class, new Annotation[0]);
        ErrorBean errorBean = converter.convert(response.errorBody());
        if (errorBean == null || errorBean.getMessage() == null) {
            okhttp3.Response okhttpResponse = response.raw();
            errorBean = new ErrorBean(okhttpResponse.message(), okhttpResponse.code());
        }
        return errorBean;
    }

    // ====================== HELPER METHODS ========================

    private OkHttpClient getOkHttpClient(int timeOut)
    {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(doLoggingInterceptor())
                .connectTimeout(timeOut, SECONDS)
                .readTimeout(timeOut * 2, SECONDS)
                .build();
    }

    private Interceptor doLoggingInterceptor()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BODY);
        return loggingInterceptor;
    }
}
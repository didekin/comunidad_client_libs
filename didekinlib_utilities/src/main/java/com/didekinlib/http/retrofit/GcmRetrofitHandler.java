package com.didekinlib.http.retrofit;


import com.didekinlib.http.exception.ErrorBean;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.didekinlib.http.retrofit.RetrofitUtil.getRetrofitErrorBean;
import static com.didekinlib.json.MoshiUtil.getMoshiConverterForJwk;
import static java.util.concurrent.TimeUnit.SECONDS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * User: pedro@didekin
 * Date: 05/08/15
 * Time: 20:07
 */
public class GcmRetrofitHandler implements HttpHandlerIf {

    private final Retrofit retrofit;

    public GcmRetrofitHandler(final String hostPort, int timeOut)
    {
        getMoshiConverterForJwk();
        retrofit = new Retrofit.Builder()
                .baseUrl(hostPort)
                .client(getOkHttpClient(timeOut))
                .addConverterFactory(getMoshiConverterForJwk())
                .build();
    }

    @Override
    public <T> T getService(Class<T> endPointInterface)
    {
        return retrofit.create(endPointInterface);
    }

    @Override
    public ErrorBean getErrorBean(Response<?> response) throws IOException
    {
        return getRetrofitErrorBean(response, retrofit);
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
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
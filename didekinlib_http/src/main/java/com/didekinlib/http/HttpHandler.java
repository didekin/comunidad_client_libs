package com.didekinlib.http;

import com.didekinlib.http.GsonUtil.NullOnEmptyConverterFactory;
import com.didekinlib.http.exception.ErrorBean;
import com.didekinlib.model.common.dominio.BeanBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.didekinlib.http.GsonUtil.NullOnEmptyConverterFactory.getNullConverter;
import static com.didekinlib.http.GsonUtil.getGsonConverterForJwk;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm;
import static javax.net.ssl.TrustManagerFactory.getInstance;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * User: pedro@didekin
 * Date: 05/08/15
 * Time: 20:07
 * <p>
 * Retrofit implementation of a HttpHandlerIf.
 */
public class HttpHandler implements HttpHandlerIf {

    final Retrofit retrofit;

    private HttpHandler(HttpHandlerBuilder builder)
    {
        retrofit = builder.retrofitBuilder.build();
    }

    @Override
    public <T> T getService(Class<T> endPointInterface)
    {
        return retrofit.create(endPointInterface);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public ErrorBean getErrorBean(Response<?> response) throws IOException
    {
        List<Converter.Factory> converters = retrofit.converterFactories();
        Converter<ResponseBody, ErrorBean> converter =
                retrofit.nextResponseBodyConverter(getNullConverter(retrofit).get(),ErrorBean.class, new Annotation[0]);
        ErrorBean errorBean = converter.convert(response.errorBody());
        if (errorBean == null || errorBean.getMessage() == null) {
            okhttp3.Response okhttpResponse = response.raw();
            errorBean = new ErrorBean(okhttpResponse.message(), okhttpResponse.code());
        }
        return errorBean;
    }

    //  ============================== BUILDER ====================================

    public static class HttpHandlerBuilder implements BeanBuilder<HttpHandler> {

        private Retrofit.Builder retrofitBuilder;
        private OkHttpClient.Builder okhttpClBuilder;

        /**
         * @param hostPortIn: host concatenated with listening port.
         */
        public HttpHandlerBuilder(String hostPortIn)
        {
            retrofitBuilder = new Retrofit.Builder();
            okhttpClBuilder = new OkHttpClient.Builder();
            retrofitBuilder
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(getGsonConverterForJwk())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            okhttpClBuilder
                    .addNetworkInterceptor(doLoggingInterceptor());
            retrofitBuilder.baseUrl(hostPortIn);
        }

        public HttpHandlerBuilder timeOutSec(int timeOutSeconds)
        {
            okhttpClBuilder
                    .connectTimeout(timeOutSeconds, SECONDS)
                    .readTimeout(timeOutSeconds * 2, SECONDS);
            return this;
        }

        public HttpHandlerBuilder clientInterceptor(Interceptor interceptorIn){
            okhttpClBuilder.addInterceptor(interceptorIn);
            return this;
        }

        public HttpHandlerBuilder keyStoreClient(JksInClient keyStoreInClient)
        {
            X509TrustManager trustManager = getTrustManager(keyStoreInClient);
            okhttpClBuilder.sslSocketFactory(getSslSocketFactory(trustManager), trustManager);
            return this;
        }

        public HttpHandlerBuilder converterFactory(Converter.Factory factory)
        {
            retrofitBuilder.addConverterFactory(factory);
            return this;
        }

        public HttpHandlerBuilder adapterFactory(CallAdapter.Factory factory)
        {
            retrofitBuilder.addCallAdapterFactory(factory);
            return this;
        }

        @Override
        public HttpHandler build()
        {
            retrofitBuilder.client(okhttpClBuilder.build());
            HttpHandler httpHandler = new HttpHandler(this);
            if (httpHandler.retrofit.baseUrl() == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return httpHandler;
        }

        // ====================== HELPER METHODS ======================

        private Interceptor doLoggingInterceptor()
        {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(BODY);
            return loggingInterceptor;
        }

        private X509TrustManager getTrustManager(JksInClient keyStoreClient)
        {
            KeyStore keyStore;
            TrustManagerFactory tmf;

            try {
                // Configuración cliente.
                String keyStoreType = KeyStore.getDefaultType();
                keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(keyStoreClient.getInputStream(), keyStoreClient.getJksPswd().toCharArray());
                // Create a TrustManager that trusts the CAs in our JksInAppClient
                tmf = getInstance(getDefaultAlgorithm());
                tmf.init(keyStore);

                TrustManager[] trustManagers = tmf.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException(this.getClass().getName() + ":Unexpected default trust managers:"
                            + Arrays.toString(trustManagers));
                }
                return (X509TrustManager) trustManagers[0];
            } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
                throw new RuntimeException(this.getClass().getName() + ":TrustManager not initialized");
            }
        }

        private SSLSocketFactory getSslSocketFactory(TrustManager trustManager)
        {
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                return sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                throw new RuntimeException(this.getClass().getName() + ":SSLSocketFactory not initialized");
            }
        }
    }
}
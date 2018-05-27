package com.didekinlib.http;

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

import static com.didekinlib.http.GsonUtil.getGsonConverterTokenKey;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm;
import static javax.net.ssl.TrustManagerFactory.getInstance;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * User: pedro@didekin
 * Date: 05/08/15
 * Time: 20:07
 *
 * Retrofit implementation of a HttpHandlerIf.
 */
public class HttpHandler implements HttpHandlerIf{

    private final Retrofit retrofit;

    private HttpHandler(HttpHandlerBuilder builder)
    {
        retrofit = builder.retrofitBuilder.build();
    }

    @Override
    public <T> T getService(Class<T> endPointInterface)
    {
        return retrofit.create(endPointInterface);
    }

    /**
     * There are three ways to construct your observable: Observable<BodyType>, Observable<Response<BodyType>>, or Observable<Result<BodyType>>.
     * For the first version, there's nowhere to hang non-200 response information so it is included in the exception passed to onError.
     * For the latter two, the data is encapsulated in the Response object and can be accessed by calling errorBody().
     */
    @Override
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

    //  ============================== BUILDER ====================================

    public static class HttpHandlerBuilder implements BeanBuilder<HttpHandler> {

        private Retrofit.Builder retrofitBuilder;

        public HttpHandlerBuilder()
        {
            retrofitBuilder = new Retrofit.Builder();
        }

        /**
         * @param hostPortIn: host concatenated with listening port.
         */
        public HttpHandlerBuilder(String hostPortIn)
        {
            this();
            retrofitBuilder.baseUrl(hostPortIn);
            retrofitBuilder.addConverterFactory(getGsonConverterTokenKey());
            retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }

        public HttpHandlerBuilder hostPort(String hostPortIn)
        {
            retrofitBuilder.baseUrl(hostPortIn);
            return this;
        }

        public HttpHandlerBuilder okHttpClient(int timeOutSeconds)
        {
            OkHttpClient.Builder builder = getSimpleOkhttpCl(timeOutSeconds);
            retrofitBuilder.client(builder.build());
            return this;
        }

        public HttpHandlerBuilder okHttpClient(int timeOutSeconds, JksInClient jksInAppClient)
        {
            OkHttpClient.Builder builder = getSimpleOkhttpCl(timeOutSeconds);
            X509TrustManager trustManager = getTrustManager(jksInAppClient);
            builder.sslSocketFactory(getSslSocketFactory(trustManager), trustManager);
            retrofitBuilder.client(builder.build());
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

        private OkHttpClient.Builder getSimpleOkhttpCl(int timeOutSeconds)
        {
            return new OkHttpClient.Builder()
                    .addNetworkInterceptor(doLoggingInterceptor())
                    .connectTimeout(timeOutSeconds, SECONDS)
                    .readTimeout(timeOutSeconds * 2, SECONDS);
        }

        private X509TrustManager getTrustManager(JksInClient jksInAppClient)
        {
            KeyStore keyStore;
            TrustManagerFactory tmf;

            try {
                // Configuración cliente.
                String keyStoreType = KeyStore.getDefaultType();
                keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(jksInAppClient.getInputStream(), jksInAppClient.getJksPswd().toCharArray());
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
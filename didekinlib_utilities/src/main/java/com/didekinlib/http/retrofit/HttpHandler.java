package com.didekinlib.http.retrofit;

import com.didekinlib.BeanBuilder;
import com.didekinlib.http.JksInClient;
import com.didekinlib.http.exception.ErrorBean;
import com.didekinlib.json.MoshiUtil.NullOnEmptyConverterFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.didekinlib.http.retrofit.RetrofitUtil.getRetrofitErrorBean;
import static com.didekinlib.json.MoshiUtil.getMoshiConverterForJwk;
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

    @Override
    public ErrorBean getErrorBean(Response<?> response) throws IOException
    {
        return getRetrofitErrorBean(response, retrofit);
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }

    //  ============================== BUILDER ====================================

    public static class HttpHandlerBuilder implements BeanBuilder<HttpHandler> {

        private Retrofit.Builder retrofitBuilder;
        private OkHttpClient.Builder okhttpClBuilder;

        /**
         * @param hostPortIn: host concatenated with listening port.
         */
        public HttpHandlerBuilder(@Nonnull String hostPortIn)
        {
            retrofitBuilder = new Retrofit.Builder();
            okhttpClBuilder = new OkHttpClient.Builder();
            retrofitBuilder
                    // TODO: testar si es necesario este converter.
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(getMoshiConverterForJwk())
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

        public HttpHandlerBuilder clientInterceptor(Interceptor interceptorIn)
        {
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
            return new HttpHandler(this);
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
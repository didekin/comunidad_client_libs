package com.didekinlib.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Optional;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * User: pedro@didekin
 * Date: 09/05/2018
 * Time: 10:47
 */
public final class GsonUtil {

    private GsonUtil()
    {
    }

    public static GsonConverterFactory getGsonConverterForJwk()
    {
        return GsonConverterFactory.create(
                new GsonBuilder()
                        .setLenient()
                        .create());
    }

    public static <T> String objectToJsonStr(T object)
    {
        return new Gson().toJson(object);
    }

    // =============================== HELPER CLASSES ===============================

    static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit)
        {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return (Converter<ResponseBody, Object>) body -> {
                if (body.contentLength() == 0) {
                    return null;
                }
                return delegate.convert(body);
            };
        }

        public static Optional<Converter.Factory> getNullConverter(Retrofit retrofit)
        {
            return retrofit.converterFactories().stream()
                    .filter(NullOnEmptyConverterFactory.class::isInstance)
                    .findFirst();
        }
    }
}

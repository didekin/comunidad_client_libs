package com.didekinlib.json;

import com.squareup.moshi.Moshi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static retrofit2.converter.moshi.MoshiConverterFactory.create;

/**
 * User: pedro@didekin
 * Date: 2019-01-13
 * Time: 20:29
 */
public class MoshiUtil {

    private MoshiUtil()
    {
    }

    public static <T> String toJsonStr(T objectToJson)
    {
        Moshi moshi = new Moshi.Builder().build();
        @SuppressWarnings("unchecked") Class<T> objectClass = (Class<T>) objectToJson.getClass();
        return moshi.adapter(objectClass).nonNull().toJson(objectToJson);
    }

    public static MoshiConverterFactory getMoshiConverterForJwk()
    {
        // TODO: quitar lenient cuando no exista JWT.
        return create().asLenient().withNullSerialization();
    }

    public static MoshiConverterFactory getMoshiConverterForGcm()
    {
        return create();
    }

    // TODO: borrar esta clase tras testar que no es necesaria con .withNullSerialization().
    public static class NullOnEmptyConverterFactory extends Converter.Factory {

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

        public static Converter.Factory getNullConverter(Retrofit retrofit)
        {
            List<Converter.Factory> factories = retrofit.converterFactories();
            for (Converter.Factory factory : factories) {
                if (factory instanceof NullOnEmptyConverterFactory) {
                    return factory;
                }
            }
            return null;
        }
    }
}

package com.didekinlib.http.retrofit;

import com.didekinlib.http.exception.ErrorBean;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.didekinlib.json.MoshiUtil.NullOnEmptyConverterFactory.getNullConverter;
import static java.util.Objects.requireNonNull;

/**
 * User: pedro@didekin
 * Date: 2019-01-16
 * Time: 10:58
 */
public final class RetrofitUtil {

    private RetrofitUtil()
    {
    }

    public static ErrorBean getRetrofitErrorBean(Response<?> response, Retrofit retrofit) throws IOException
    {
        List<Converter.Factory> converters = retrofit.converterFactories();
        Converter<ResponseBody, ErrorBean> converter =
                retrofit.nextResponseBodyConverter(getNullConverter(retrofit), ErrorBean.class, new Annotation[0]);
        ErrorBean errorBean = converter.convert(requireNonNull(response.errorBody()));
        if (errorBean == null || errorBean.getMessage() == null) {
            okhttp3.Response okhttpResponse = response.raw();
            errorBean = new ErrorBean(okhttpResponse.message(), okhttpResponse.code());
        }
        return errorBean;
    }
}

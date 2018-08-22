package com.didekinlib.http;

import com.didekinlib.http.exception.ErrorBean;
import com.google.gson.Gson;

import org.junit.Test;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static com.didekinlib.http.GsonUtil.NullOnEmptyConverterFactory.getNullConverter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static retrofit2.converter.gson.GsonConverterFactory.create;

/**
 * User: pedro@didekin
 * Date: 22/08/2018
 * Time: 16:01
 */
public class GsonUtilTest {

    @Test
    public void test_GetGsonConverterForJwk()
    {
        HttpHandler httpHandler = new HttpHandler.HttpHandlerBuilder("http://www.didekin.es").build();
        Retrofit myRetrofit = httpHandler.retrofit;
        assertThat(getNullConverter(myRetrofit).isPresent(), is(true));
        Converter<ResponseBody, ErrorBean> converter = myRetrofit.nextResponseBodyConverter(getNullConverter(myRetrofit).get(), ErrorBean.class, new Annotation[0]);
        assertThat(create(new Gson()).responseBodyConverter(ErrorBean.class, new Annotation[0], myRetrofit).getClass().isInstance(converter),
                is(true));
    }
}
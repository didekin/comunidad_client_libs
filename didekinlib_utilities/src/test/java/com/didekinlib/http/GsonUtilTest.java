package com.didekinlib.http;

import com.didekinlib.http.exception.ErrorBean;
import com.didekinlib.http.retrofit.HttpHandler;

import org.junit.Test;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static com.didekinlib.json.MoshiUtil.NullOnEmptyConverterFactory.getNullConverter;
import static com.didekinlib.json.MoshiUtil.getMoshiConverterForJwk;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 22/08/2018
 * Time: 16:01
 */
public class GsonUtilTest {

    @Test
    public void test_getMoshiConverterForJwk()
    {
        HttpHandler httpHandler = new HttpHandler.HttpHandlerBuilder("http://www.didekin.es").build();
        Retrofit myRetrofit = httpHandler.getRetrofit();
        assertThat(getNullConverter(myRetrofit), notNullValue());

        Converter<ResponseBody, ErrorBean> converter =
                myRetrofit.nextResponseBodyConverter(getNullConverter(myRetrofit), ErrorBean.class, new Annotation[0]);
        assertThat(
                getMoshiConverterForJwk().responseBodyConverter(ErrorBean.class, new Annotation[0], myRetrofit).getClass().isInstance(converter),
                is(true));
    }
}
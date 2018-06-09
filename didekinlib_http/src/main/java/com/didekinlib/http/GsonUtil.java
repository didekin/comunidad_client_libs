package com.didekinlib.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public static GsonConverterFactory getGsonConverterTokenKey()
    {
        return GsonConverterFactory.create(
                new GsonBuilder()
                        .setLenient()
                        .create());
    }

    public static <T> String objectToJsonStr(T object){
        return new Gson().toJson(object);
    }

    // =============================== HELPER CLASSES ===============================

}

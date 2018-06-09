package com.didekinlib.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.jose4j.jwk.JsonWebKey;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

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
//                        .registerTypeAdapter(JsonWebKey.class, new JwkDeserializer())
                        .create());
    }

    public static <T> String objectToJsonStr(T object){
        return new Gson().toJson(object);
    }

    // =============================== HELPER CLASSES ===============================

    private static class JwkDeserializer implements JsonDeserializer<Map<String, Object>> {

        public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException
        {
            return context.deserialize(json, LinkedHashMap.class);
            /*try {
                return newJwk(jwkParameters);
            } catch (JoseException e) {
                throw new JsonParseException("Unable to create JWK Object when parsing JSON", e);
            }*/
        }
    }
}

package com.didekinlib.http.auth;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.lang.JoseException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.didekinlib.http.auth.AuthConstant.PK_TOKEN_PATH;
import static com.didekinlib.http.auth.AuthConstant.TOKEN_PATH;
import static com.didekinlib.http.usuario.UsuarioServConstant.PSWD_PARAM;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_PARAM;
import static org.jose4j.jwk.JsonWebKey.Factory.newJwk;

/**
 * User: pedro@didekin
 * Date: 04/09/15
 * Time: 13:40
 */
public interface AuthEndPoints {

    @GET(PK_TOKEN_PATH)
    Call<String> getPublicKey();

    @FormUrlEncoded
    @POST(TOKEN_PATH)
    Call<String> getPasswordUserToken(@Field(USER_PARAM) String username,
                                      @Field(PSWD_PARAM) String password);
}
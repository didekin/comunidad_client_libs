package com.didekinlib.http.auth;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.didekinlib.http.auth.AuthConstant.TOKEN_PATH;
import static com.didekinlib.http.usuario.UsuarioServConstant.PSWD_PARAM;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_PARAM;

/**
 * User: pedro@didekin
 * Date: 04/09/15
 * Time: 13:40
 */
public interface AuthEndPoints {

    @FormUrlEncoded
    @POST(TOKEN_PATH)
    Single<String> getPasswordUserToken(@Field(USER_PARAM) String username,
                                        @Field(PSWD_PARAM) String password,
                                        @Field(PSWD_PARAM) String appID);
}
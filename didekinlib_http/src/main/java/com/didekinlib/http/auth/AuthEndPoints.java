package com.didekinlib.http.auth;

import com.didekinlib.http.ErrorBean;
import com.didekinlib.http.usuario.UsuarioServConstant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * User: pedro@didekin
 * Date: 04/09/15
 * Time: 13:40
 */
@SuppressWarnings("unused")
public interface AuthEndPoints {

    @FormUrlEncoded
    @POST(AuthConstant.TOKEN_PATH)
    Call<SpringOauthToken> getPasswordUserToken(@Header("Authorization") String authClient
            , @Field(UsuarioServConstant.USER_PARAM) String username
            , @Field(UsuarioServConstant.PSWD_PARAM) String password
            , @Field(AuthConstant.GRANT_TYPE_PARAM) String grantType);

    @FormUrlEncoded
    @POST(AuthConstant.TOKEN_PATH)
    Call<SpringOauthToken> getRefreshUserToken(@Header("Authorization") String authClient
            , @Field(AuthConstant.REFRESH_TK_PARAM) String refreshToken
            , @Field(AuthConstant.GRANT_TYPE_PARAM) String grantType);

    @GET("/open/not_found")
    Call<ErrorBean> getNotFoundMsg();
}
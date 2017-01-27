package com.didekinlib.http.retrofit;

import com.didekinlib.http.ErrorBean;
import com.didekinlib.http.oauth2.OauthConstant;
import com.didekinlib.http.oauth2.SpringOauthToken;
import com.didekinlib.http.UsuarioServConstant;

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
public interface Oauth2EndPoints {

    @FormUrlEncoded
    @POST(OauthConstant.TOKEN_PATH)
    Call<SpringOauthToken> getPasswordUserToken(@Header("Authorization") String authClient
            , @Field(UsuarioServConstant.USER_PARAM) String username
            , @Field(UsuarioServConstant.PSWD_PARAM) String password
            , @Field(OauthConstant.GRANT_TYPE_PARAM) String grantType);

    @FormUrlEncoded
    @POST(OauthConstant.TOKEN_PATH)
    Call<SpringOauthToken> getRefreshUserToken(@Header("Authorization") String authClient
            , @Field(OauthConstant.REFRESH_TK_PARAM) String refreshToken
            , @Field(OauthConstant.GRANT_TYPE_PARAM) String grantType);

    @GET("/open/not_found")
    Call<ErrorBean> getNotFoundMsg();
}
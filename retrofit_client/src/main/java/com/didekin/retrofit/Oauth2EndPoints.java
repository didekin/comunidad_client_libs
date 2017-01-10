package com.didekin.retrofit;

import com.didekin.http.ErrorBean;
import com.didekin.http.oauth2.OauthConstant;
import com.didekin.http.oauth2.SpringOauthToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.didekin.http.oauth2.OauthConstant.GRANT_TYPE_PARAM;
import static com.didekin.http.oauth2.OauthConstant.REFRESH_TK_PARAM;
import static com.didekin.http.UsuarioServConstant.PSWD_PARAM;
import static com.didekin.http.UsuarioServConstant.USER_PARAM;

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
            , @Field(USER_PARAM) String username
            , @Field(PSWD_PARAM) String password
            , @Field(GRANT_TYPE_PARAM) String grantType);

    @FormUrlEncoded
    @POST(OauthConstant.TOKEN_PATH)
    Call<SpringOauthToken> getRefreshUserToken(@Header("Authorization") String authClient
            , @Field(REFRESH_TK_PARAM) String refreshToken
            , @Field(GRANT_TYPE_PARAM) String grantType);

    @GET("/open/not_found")
    Call<ErrorBean> getNotFoundMsg();
}
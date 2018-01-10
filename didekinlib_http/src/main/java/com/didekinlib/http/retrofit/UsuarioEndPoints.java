package com.didekinlib.http.retrofit;

import com.didekinlib.model.usuario.GcmTokenWrapper;
import com.didekinlib.model.usuario.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.didekinlib.http.CommonServConstant.ACCEPT_LANGUAGE;
import static com.didekinlib.http.CommonServConstant.MIME_JSON;
import static com.didekinlib.http.UsuarioServConstant.ACCESS_TOKEN_DELETE;
import static com.didekinlib.http.UsuarioServConstant.GCM_TOKEN_PARAM;
import static com.didekinlib.http.UsuarioServConstant.LOGIN;
import static com.didekinlib.http.UsuarioServConstant.PASSWORD_MODIFY;
import static com.didekinlib.http.UsuarioServConstant.PASSWORD_SEND;
import static com.didekinlib.http.UsuarioServConstant.PSWD_PARAM;
import static com.didekinlib.http.UsuarioServConstant.USER_DELETE;
import static com.didekinlib.http.UsuarioServConstant.USER_PARAM;
import static com.didekinlib.http.UsuarioServConstant.USER_READ;
import static com.didekinlib.http.UsuarioServConstant.USER_READ_GCM_TOKEN;
import static com.didekinlib.http.UsuarioServConstant.USER_WRITE;
import static com.didekinlib.http.UsuarioServConstant.USER_WRITE_GCM_TOKEN;

/**
 * User: pedro@didekin
 * Date: 07/06/15
 * Time: 14:13
 */
@SuppressWarnings("unused")
public interface UsuarioEndPoints {

    @DELETE(ACCESS_TOKEN_DELETE + "/{oldTk}")
    Call<Boolean> deleteAccessToken(@Header("Authorization") String accessToken, @Path("oldTk") String oldAccessToken);

    @DELETE(USER_DELETE)
    Call<Boolean> deleteUser(@Header("Authorization") String accessToken);

    @Headers({
            "Content-Type:" + MIME_JSON
    })
    @GET(USER_READ_GCM_TOKEN)
    Call<GcmTokenWrapper> getGcmToken(@Header("Authorization") String accessToken);

    @GET(USER_READ)
    Call<Usuario> getUserData(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST(LOGIN)
    Call<Boolean> login(@Field(USER_PARAM) String userName, @Field(PSWD_PARAM) String password);

    @FormUrlEncoded
    @POST(USER_WRITE_GCM_TOKEN)
    Call<Integer> modifyUserGcmToken(@Header("Authorization") String accessToken, @Field(GCM_TOKEN_PARAM) String gcmToken);

    @PUT(USER_WRITE)
    Call<Integer> modifyUser(@Header(ACCEPT_LANGUAGE) String localeToStr,
                             @Header("Authorization") String accessToken,
                             @Body Usuario usuario);

    @FormUrlEncoded
    @POST(PASSWORD_MODIFY)
    Call<Integer> passwordChange(@Header("Authorization") String accessToken, @Field(PSWD_PARAM) String password);

    @FormUrlEncoded
    @POST(PASSWORD_SEND)
    Call<Boolean> passwordSend(@Header(ACCEPT_LANGUAGE) String localeToStr, @Field(USER_PARAM) String userName);
}
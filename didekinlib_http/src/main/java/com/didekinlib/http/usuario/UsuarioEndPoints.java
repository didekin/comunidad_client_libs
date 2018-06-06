package com.didekinlib.http.usuario;

import com.didekinlib.model.usuario.Usuario;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import static com.didekinlib.http.CommonServConstant.ACCEPT_LANGUAGE;
import static com.didekinlib.http.usuario.UsuarioServConstant.APP_ID_PARAM;
import static com.didekinlib.http.usuario.UsuarioServConstant.LOGIN;
import static com.didekinlib.http.usuario.UsuarioServConstant.OLD_PSWD_PARAM;
import static com.didekinlib.http.usuario.UsuarioServConstant.PASSWORD_MODIFY;
import static com.didekinlib.http.usuario.UsuarioServConstant.PASSWORD_SEND;
import static com.didekinlib.http.usuario.UsuarioServConstant.PSWD_PARAM;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_DELETE;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_PARAM;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_READ;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_WRITE;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_WRITE_GCM_TOKEN;

/**
 * User: pedro@didekin
 * Date: 07/06/15
 * Time: 14:13
 */
public interface UsuarioEndPoints {

    @DELETE(USER_DELETE)
    Single<Response<Boolean>> deleteUser(@Header("Authorization") String accessToken);

    @GET(USER_READ)
        // Remember: to be used also for retrieving gcmToken.
    Single<Response<Usuario>> getUserData(@Header("Authorization") String accessToken);

    /**
     * @return token to be stored in local, with userName and appID (gcmToken).
     */
    @FormUrlEncoded
    @POST(LOGIN)
    Single<Response<String>> login(@Field(USER_PARAM) String userName,
                                   @Field(PSWD_PARAM) String password,
                                   @Field(APP_ID_PARAM) String appID);

    @FormUrlEncoded
    @POST(USER_WRITE_GCM_TOKEN)
    Single<Response<String>> modifyUserGcmToken(@Header("Authorization") String accessToken, @Field(APP_ID_PARAM) String gcmToken);

    @PUT(USER_WRITE)
    Single<Response<Integer>> modifyUser(@Header(ACCEPT_LANGUAGE) String localeToStr,
                             @Header("Authorization") String accessToken,
                             @Body Usuario usuario);

    @FormUrlEncoded
    @POST(PASSWORD_MODIFY)
    Single<Response<String>> passwordChange(@Header("Authorization") String accessToken,
                                            @Field(OLD_PSWD_PARAM) String oldPassword,
                                            @Field(PSWD_PARAM) String newPassword);

    @FormUrlEncoded
    @POST(PASSWORD_SEND)
    Single<Response<Boolean>> passwordSend(@Header(ACCEPT_LANGUAGE) String localeToStr, @Field(USER_PARAM) String userName);
}
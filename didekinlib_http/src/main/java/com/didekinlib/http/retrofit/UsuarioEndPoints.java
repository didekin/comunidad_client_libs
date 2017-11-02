package com.didekinlib.http.retrofit;

import com.didekinlib.http.CommonServConstant;
import com.didekinlib.http.UsuarioServConstant;
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

/**
 * User: pedro@didekin
 * Date: 07/06/15
 * Time: 14:13
 */
@SuppressWarnings("unused")
public interface UsuarioEndPoints {

    @DELETE(UsuarioServConstant.ACCESS_TOKEN_DELETE + "/{oldTk}")
    Call<Boolean> deleteAccessToken(@Header("Authorization") String accessToken, @Path("oldTk") String oldAccessToken);

    @DELETE(UsuarioServConstant.USER_DELETE)
    Call<Boolean> deleteUser(@Header("Authorization") String accessToken);

    @Headers({
            "Content-Type:" + CommonServConstant.MIME_JSON
    })
    @GET(UsuarioServConstant.USER_READ_GCM_TOKEN)
    Call<GcmTokenWrapper> getGcmToken(@Header("Authorization") String accessToken);

    @GET(UsuarioServConstant.USER_READ)
    Call<Usuario> getUserData(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST(UsuarioServConstant.LOGIN)
    Call<Boolean> login(@Field(UsuarioServConstant.USER_PARAM) String userName, @Field(UsuarioServConstant.PSWD_PARAM) String password);

    @FormUrlEncoded
    @POST(UsuarioServConstant.USER_WRITE_GCM_TOKEN)
    Call<Integer> modifyUserGcmToken(@Header("Authorization") String accessToken, @Field(UsuarioServConstant.GCM_TOKEN_PARAM) String gcmToken);

    @PUT(UsuarioServConstant.USER_WRITE)
    Call<Integer> modifyUser(@Header("Authorization") String accessToken, @Body Usuario usuario);

    @FormUrlEncoded
    @POST(UsuarioServConstant.PASSWORD_MODIFY)
    Call<Integer> passwordChange(@Header("Authorization") String accessToken, @Field(UsuarioServConstant.PSWD_PARAM) String password);

    @FormUrlEncoded
    @POST(UsuarioServConstant.PASSWORD_SEND)
    Call<Boolean> passwordSend(@Field(UsuarioServConstant.USER_PARAM) String userName);
}
package com.didekin.usuario;

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
import rx.Observable;

import static com.didekin.common.controller.CommonServiceConstant.MIME_JSON;

/**
 * User: pedro@didekin
 * Date: 07/06/15
 * Time: 14:13
 */
@SuppressWarnings("unused")
public interface UsuarioEndPoints {

    // Code constants.
    int IS_USER_DELETED = -1;
    /*Params used by the authorization server*/
    String USER_PARAM = "username";
    String PSWD_PARAM = "password";
    String GCM_TOKEN_PARAM = "gcmtoken";
    /*Paths used by the resource server.*/
    String OPEN = "/open";
    String OPEN_AREA = OPEN + "/**";

    // Paths.
    String USER_PATH = "/usuario";
    String USER_WRITE = USER_PATH + "/write";
    String ACCESS_TOKEN_DELETE = USER_WRITE + "/token/delete";
    String LOGIN = OPEN + "/login";
    String PASSWORD_MODIFY = USER_WRITE + "/pswd";
    String PASSWORD_SEND = OPEN + "/pswd_send";
    String USER_DELETE = USER_WRITE + "/delete";
    String USER_READ = USER_PATH + "/read";
    String USER_READ_GCM_TOKEN = USER_READ + "/gcm_token";
    String USER_WRITE_GCM_TOKEN = USER_WRITE + "/gcm_token";

    @DELETE(ACCESS_TOKEN_DELETE + "/{oldTk}")
    Call<Boolean> deleteAccessToken(@Header("Authorization") String accessToken, @Path("oldTk") String oldAccessToken);

    @DELETE(USER_DELETE)
    Observable<Boolean> deleteUser(@Header("Authorization") String accessToken);

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
    Call<Integer> modifyUser(@Header("Authorization") String accessToken, @Body Usuario usuario);

    @FormUrlEncoded
    @POST(PASSWORD_MODIFY)
    Call<Integer> passwordChange(@Header("Authorization") String accessToken, @Field(PSWD_PARAM) String password);

    @FormUrlEncoded
    @POST(PASSWORD_SEND)
    Call<Boolean> passwordSend(@Field(USER_PARAM) String userName);
}
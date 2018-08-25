package com.didekinlib.http.usuario;

import com.didekinlib.model.usuario.Usuario;

import io.reactivex.Single;
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

/**
 * User: pedro@didekin
 * Date: 07/06/15
 * Time: 14:13
 *
 * Note:
 * There are three ways to construct your observable: Observable<BodyType>, Observable<Response<BodyType>>, or Observable<Result<BodyType>>.
 * For the first version, there's nowhere to hang non-200 response information so it is included in the exception passed to onError.
 * For the latter two, the data is encapsulated in the Response object and can be accessed by calling errorBody().
 */
public interface UsuarioEndPoints {

    @DELETE(USER_DELETE)
    Single<Response<Boolean>> deleteUser(@Header("Authorization") String authHeader);

    @GET(USER_READ)
        // Remember: to be used also for retrieving gcmToken.
    Single<Response<Usuario>> getUserData(@Header("Authorization") String authHeader);

    /**
     * @return token to be stored in local, with userName and appID (gcmToken).
     */
    @FormUrlEncoded
    @POST(LOGIN)
    Single<Response<String>> login(@Field(USER_PARAM) String userName,
                                   @Field(PSWD_PARAM) String password,
                                   @Field(APP_ID_PARAM) String appID);

    @PUT(USER_WRITE)
    Single<Response<Integer>> modifyUser(@Header(ACCEPT_LANGUAGE) String localeToStr,
                                         @Header("Authorization") String authHeader,
                                         @Body Usuario usuario);

    @FormUrlEncoded
    @POST(PASSWORD_MODIFY)
    Single<Response<String>> passwordChange(@Header("Authorization") String authHeader,
                                            @Field(OLD_PSWD_PARAM) String oldPassword,
                                            @Field(PSWD_PARAM) String newPassword);

    @FormUrlEncoded
    @POST(PASSWORD_SEND)
    Single<Response<Boolean>> passwordSend(@Header(ACCEPT_LANGUAGE) String localeToStr, @Field(USER_PARAM) String userName);
}
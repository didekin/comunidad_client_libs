package com.didekinlib.model.usuario.http;


import com.didekinlib.model.usuariocomunidad.UsuarioComunidad;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.didekinlib.model.usuario.http.UsuarioServConstant.OPEN;
import static com.didekinlib.model.usuario.http.UsuarioServConstant.USER_PARAM;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 13:33
 *
 * Simplified back-door for creating user state in remote. Only for tests.
 */
public interface UserMockEndPoints {

    // PATHS for client tests.
    String mockPath = OPEN + "/mock";
    String regComu_User_UserComu = mockPath + "/reg_comu_user_usercomu";
    String regUser_UserComu = mockPath + "/reg_user_usercomu";
    String user_delete = mockPath + "/user_delete";

    @POST(regComu_User_UserComu)
    Single<Response<String>> regComuAndUserAndUserComu(@Body UsuarioComunidad usuarioCom);

    @POST(regUser_UserComu)
    Single<Response<String>> regUserAndUserComu(@Body UsuarioComunidad userCom);

    @FormUrlEncoded
    @POST(user_delete)
    Single<Response<Boolean>> deleteUser(@Field(USER_PARAM) String userName);

    @GET("{mock_path}/{mock2_path}")
    Call<String> tryTokenInterceptor(@Header("Authorization") String accessToken,
                                     @Path("mock_path") String mock_path,
                                     @Path("mock2_path") String mock2_path);
}

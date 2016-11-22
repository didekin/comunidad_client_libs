package com.didekin.usuariocomunidad;

import com.didekin.comunidad.Comunidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.didekin.comunidad.ComunidadEndPoints.COMUNIDAD_PATH;
import static com.didekin.comunidad.ComunidadEndPoints.COMUNIDAD_READ;
import static com.didekin.usuario.UsuarioEndPoints.OPEN;
import static com.didekin.usuario.UsuarioEndPoints.USER_READ;
import static com.didekin.usuario.UsuarioEndPoints.USER_WRITE;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 13:33
 */
@SuppressWarnings("unused")
public interface UsuarioComunidadEndPoints {

    String COMUNIDAD_WRITE = COMUNIDAD_PATH + "/write";
    String COMUNIDAD_OLDEST_USER = COMUNIDAD_READ + "/oldest_user";
    String COMUS_BY_USER = USER_READ + "/comus_by_user";
    String REG_COMU_AND_USER_AND_USERCOMU = OPEN + "/reg_comu_user_usercomu";
    String REG_COMU_USERCOMU = USER_WRITE + "/reg_comu_usercomu";
    String REG_USER_USERCOMU = OPEN + "/reg_user_usercomu";
    String REG_USERCOMU = USER_WRITE + "/reg_usercomu";
    String USERCOMU_DELETE = USER_WRITE + "/usercomus/delete";
    String USERCOMU_MODIFY = USER_WRITE + "/usercomus";
    String USERCOMU_READ = USER_READ + "/usercomus";
    String USERCOMUS_BY_COMU = COMUNIDAD_READ + "/usercomus_by_comu";
    String USERCOMUS_BY_USER = USER_READ + "/usercomus_by_user";

    @DELETE(USERCOMU_DELETE + "/{comunidadId}")
    Call<Integer> deleteUserComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @GET(COMUS_BY_USER)
    Call<List<Comunidad>> getComusByUser(@Header("Authorization") String accessToken);

    @GET(USERCOMU_READ + "/{comunidadId}")
    Call<UsuarioComunidad> getUserComuByUserAndComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @GET(COMUNIDAD_OLDEST_USER + "/{comunidadId}")
    Call<Boolean> isOldestOrAdmonUserComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @PUT(COMUNIDAD_WRITE)
    Call<Integer> modifyComuData(@Header("Authorization") String accessToken, @Body Comunidad comunidad);

    @PUT(USERCOMU_MODIFY)
    Call<Integer> modifyUserComu(@Header("Authorization") String accessToken, @Body UsuarioComunidad usuarioComunidad);

    @POST(REG_COMU_AND_USER_AND_USERCOMU)
    Call<Boolean> regComuAndUserAndUserComu(@Body UsuarioComunidad usuarioCom);

    @POST(REG_COMU_USERCOMU)
    Call<Boolean> regComuAndUserComu(@Header("Authorization") String accessToken,
                                     @Body UsuarioComunidad usuarioCom);

    @POST(REG_USER_USERCOMU)
    Call<Boolean> regUserAndUserComu(@Body UsuarioComunidad userCom);

    @POST(REG_USERCOMU)
    Call<Integer> regUserComu(@Header("Authorization") String accessToken, @Body UsuarioComunidad usuarioComunidad);

    @GET(USERCOMUS_BY_COMU + "/{comunidadId}")
    Call<List<UsuarioComunidad>> seeUserComusByComu(@Header("Authorization") String accessToken,
                                                    @Path("comunidadId") long comunidadId);

    @GET(USERCOMUS_BY_USER)
    Call<List<UsuarioComunidad>> seeUserComusByUser(@Header("Authorization") String accessToken);
}

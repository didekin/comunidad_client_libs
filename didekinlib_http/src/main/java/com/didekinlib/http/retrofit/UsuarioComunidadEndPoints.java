package com.didekinlib.http.retrofit;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 13:33
 */
@SuppressWarnings("unused")
public interface UsuarioComunidadEndPoints {

    @DELETE(UsuarioComunidadServConstant.USERCOMU_DELETE + "/{comunidadId}")
    Call<Integer> deleteUserComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @GET(UsuarioComunidadServConstant.COMUS_BY_USER)
    Call<List<Comunidad>> getComusByUser(@Header("Authorization") String accessToken);

    @GET(UsuarioComunidadServConstant.USERCOMU_READ + "/{comunidadId}")
    Call<UsuarioComunidad> getUserComuByUserAndComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @GET(UsuarioComunidadServConstant.COMUNIDAD_OLDEST_USER + "/{comunidadId}")
    Call<Boolean> isOldestOrAdmonUserComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @PUT(UsuarioComunidadServConstant.COMUNIDAD_WRITE)
    Call<Integer> modifyComuData(@Header("Authorization") String accessToken, @Body Comunidad comunidad);

    @PUT(UsuarioComunidadServConstant.USERCOMU_MODIFY)
    Call<Integer> modifyUserComu(@Header("Authorization") String accessToken, @Body UsuarioComunidad usuarioComunidad);

    @POST(UsuarioComunidadServConstant.REG_COMU_AND_USER_AND_USERCOMU)
    Call<Boolean> regComuAndUserAndUserComu(@Body UsuarioComunidad usuarioCom);

    @POST(UsuarioComunidadServConstant.REG_COMU_USERCOMU)
    Call<Boolean> regComuAndUserComu(@Header("Authorization") String accessToken,
                                     @Body UsuarioComunidad usuarioCom);

    @POST(UsuarioComunidadServConstant.REG_USER_USERCOMU)
    Call<Boolean> regUserAndUserComu(@Body UsuarioComunidad userCom);

    @POST(UsuarioComunidadServConstant.REG_USERCOMU)
    Call<Integer> regUserComu(@Header("Authorization") String accessToken, @Body UsuarioComunidad usuarioComunidad);

    @GET(UsuarioComunidadServConstant.USERCOMUS_BY_COMU + "/{comunidadId}")
    Call<List<UsuarioComunidad>> seeUserComusByComu(@Header("Authorization") String accessToken,
                                                    @Path("comunidadId") long comunidadId);

    @GET(UsuarioComunidadServConstant.USERCOMUS_BY_USER)
    Call<List<UsuarioComunidad>> seeUserComusByUser(@Header("Authorization") String accessToken);
}

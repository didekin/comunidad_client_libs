package com.didekinlib.http.usuariocomunidad;


import com.didekinlib.model.comunidad.Comunidad;
import com.didekinlib.model.usuariocomunidad.UsuarioComunidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.didekinlib.http.CommonServConstant.ACCEPT_LANGUAGE;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.COMUNIDAD_OLDEST_USER;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.COMUNIDAD_WRITE;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.COMUS_BY_USER;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.REG_COMU_AND_USER_AND_USERCOMU;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.REG_COMU_USERCOMU;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.REG_USERCOMU;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.REG_USER_USERCOMU;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.USERCOMUS_BY_COMU;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.USERCOMUS_BY_USER;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.USERCOMU_DELETE;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.USERCOMU_MODIFY;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.USERCOMU_READ;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 13:33
 */
@SuppressWarnings("unused")
public interface UsuarioComunidadEndPoints {

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
    Call<Boolean> regComuAndUserAndUserComu(@Header(ACCEPT_LANGUAGE) String localeToStr, @Body UsuarioComunidad usuarioCom);

    @POST(REG_COMU_USERCOMU)
    Call<Boolean> regComuAndUserComu(@Header("Authorization") String accessToken,
                                     @Body UsuarioComunidad usuarioCom);

    @POST(REG_USER_USERCOMU)
    Call<Boolean> regUserAndUserComu(@Header(ACCEPT_LANGUAGE) String localeToStr, @Body UsuarioComunidad userCom);

    @POST(REG_USERCOMU)
    Call<Integer> regUserComu(@Header("Authorization") String accessToken, @Body UsuarioComunidad usuarioComunidad);

    @GET(USERCOMUS_BY_COMU + "/{comunidadId}")
    Call<List<UsuarioComunidad>> seeUserComusByComu(@Header("Authorization") String accessToken,
                                                    @Path("comunidadId") long comunidadId);

    @GET(USERCOMUS_BY_USER)
    Call<List<UsuarioComunidad>> seeUserComusByUser(@Header("Authorization") String accessToken);
}
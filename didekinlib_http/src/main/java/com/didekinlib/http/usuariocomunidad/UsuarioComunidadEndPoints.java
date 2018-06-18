package com.didekinlib.http.usuariocomunidad;


import com.didekinlib.model.comunidad.Comunidad;
import com.didekinlib.model.usuariocomunidad.UsuarioComunidad;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Response;
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

public interface UsuarioComunidadEndPoints {

    @DELETE(USERCOMU_DELETE + "/{comunidadId}")
    Single<Response<Integer>> deleteUserComu(@Header("Authorization") String authHeader, @Path("comunidadId") long comunidadId);

    @GET(COMUS_BY_USER)
    Single<Response<List<Comunidad>>> getComusByUser(@Header("Authorization") String authHeader);

    @GET(USERCOMU_READ + "/{comunidadId}")
    Maybe<Response<UsuarioComunidad>> getUserComuByUserAndComu(@Header("Authorization") String authHeader, @Path("comunidadId") long comunidadId);

    @GET(COMUNIDAD_OLDEST_USER + "/{comunidadId}")
    Single<Response<Boolean>> isOldestOrAdmonUserComu(@Header("Authorization") String authHeader, @Path("comunidadId") long comunidadId);

    @PUT(COMUNIDAD_WRITE)
    Single<Response<Integer>> modifyComuData(@Header("Authorization") String authHeader, @Body Comunidad comunidad);

    @PUT(USERCOMU_MODIFY)
    Single<Response<Integer>> modifyUserComu(@Header("Authorization") String authHeader, @Body UsuarioComunidad usuarioComunidad);

    @POST(REG_COMU_AND_USER_AND_USERCOMU)
    Single<Response<Boolean>> regComuAndUserAndUserComu(@Header(ACCEPT_LANGUAGE) String localeToStr, @Body UsuarioComunidad usuarioCom);

    @POST(REG_COMU_USERCOMU)
    Single<Response<Boolean>> regComuAndUserComu(@Header("Authorization") String authHeader,
                                                 @Body UsuarioComunidad usuarioCom);

    @POST(REG_USER_USERCOMU)
    Single<Response<Boolean>> regUserAndUserComu(@Header(ACCEPT_LANGUAGE) String localeToStr, @Body UsuarioComunidad userCom);

    @POST(REG_USERCOMU)
    Single<Response<Integer>> regUserComu(@Header("Authorization") String authHeader, @Body UsuarioComunidad usuarioComunidad);

    @GET(USERCOMUS_BY_COMU + "/{comunidadId}")
    Single<Response<List<UsuarioComunidad>>> seeUserComusByComu(@Header("Authorization") String authHeader,
                                                                @Path("comunidadId") long comunidadId);

    @GET(USERCOMUS_BY_USER)
    Single<Response<List<UsuarioComunidad>>> seeUserComusByUser(@Header("Authorization") String authHeader);
}

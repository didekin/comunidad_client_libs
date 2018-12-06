package com.didekinlib.model.relacion.usuariocomunidad.http;


import com.didekinlib.http.CommonServConstant;
import com.didekinlib.model.entidad.comunidad.Comunidad;
import com.didekinlib.model.relacion.usuariocomunidad.UsuarioComunidad;

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

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 13:33
 */

public interface UsuarioComunidadEndPoints {

    @DELETE(UsuarioComunidadServConstant.USERCOMU_DELETE + "/{comunidadId}")
    Single<Response<Integer>> deleteUserComu(@Header("Authorization") String authHeader, @Path("comunidadId") long comunidadId);

    @GET(UsuarioComunidadServConstant.COMUS_BY_USER)
    Single<Response<List<Comunidad>>> getComusByUser(@Header("Authorization") String authHeader);

    @GET(UsuarioComunidadServConstant.USERCOMU_READ + "/{comunidadId}")
    Maybe<Response<UsuarioComunidad>> getUserComuByUserAndComu(@Header("Authorization") String authHeader, @Path("comunidadId") long comunidadId);

    @GET(UsuarioComunidadServConstant.COMUNIDAD_OLDEST_USER + "/{comunidadId}")
    Single<Response<Boolean>> isOldestOrAdmonUserComu(@Header("Authorization") String authHeader, @Path("comunidadId") long comunidadId);

    @PUT(UsuarioComunidadServConstant.COMUNIDAD_WRITE)
    Single<Response<Integer>> modifyComuData(@Header("Authorization") String authHeader, @Body Comunidad comunidad);

    @PUT(UsuarioComunidadServConstant.USERCOMU_MODIFY)
    Single<Response<Integer>> modifyUserComu(@Header("Authorization") String authHeader, @Body UsuarioComunidad usuarioComunidad);

    @POST(UsuarioComunidadServConstant.REG_COMU_AND_USER_AND_USERCOMU)
    Single<Response<Boolean>> regComuAndUserAndUserComu(@Header(CommonServConstant.ACCEPT_LANGUAGE) String localeToStr, @Body UsuarioComunidad usuarioCom);

    @POST(UsuarioComunidadServConstant.REG_COMU_USERCOMU)
    Single<Response<Boolean>> regComuAndUserComu(@Header("Authorization") String authHeader,
                                                 @Body UsuarioComunidad usuarioCom);

    @POST(UsuarioComunidadServConstant.REG_USER_USERCOMU)
    Single<Response<Boolean>> regUserAndUserComu(@Header(CommonServConstant.ACCEPT_LANGUAGE) String localeToStr, @Body UsuarioComunidad userCom);

    @POST(UsuarioComunidadServConstant.REG_USERCOMU)
    Single<Response<Integer>> regUserComu(@Header("Authorization") String authHeader, @Body UsuarioComunidad usuarioComunidad);

    @GET(UsuarioComunidadServConstant.USERCOMUS_BY_COMU + "/{comunidadId}")
    Single<Response<List<UsuarioComunidad>>> seeUserComusByComu(@Header("Authorization") String authHeader,
                                                                @Path("comunidadId") long comunidadId);

    @GET(UsuarioComunidadServConstant.USERCOMUS_BY_USER)
    Single<Response<List<UsuarioComunidad>>> seeUserComusByUser(@Header("Authorization") String authHeader);
}

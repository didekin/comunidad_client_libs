package com.didekinlib.model.comunidad.http;

import com.didekinlib.model.comunidad.Comunidad;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 13:33
 */

public interface ComunidadEndPoints {

    @GET(ComunidadServConstant.COMUNIDAD_READ + "/{comunidadId}")
    Single<Response<Comunidad>> getComuData(@Header("Authorization") String authHeader, @Path("comunidadId") long comunidadId);

    @POST(ComunidadServConstant.COMUNIDAD_SEARCH)
    Single<Response<List<Comunidad>>> searchComunidades(@Body Comunidad comunidad);
}

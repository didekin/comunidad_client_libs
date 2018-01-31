package com.didekinlib.http.comunidad;

import com.didekinlib.model.comunidad.Comunidad;

import java.util.List;

import retrofit2.Call;
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
@SuppressWarnings("unused")
public interface ComunidadEndPoints {

    @GET(ComunidadServConstant.COMUNIDAD_READ + "/{comunidadId}")
    Call<Comunidad> getComuData(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @POST(ComunidadServConstant.COMUNIDAD_SEARCH)
    Call<List<Comunidad>> searchComunidades(@Body Comunidad comunidad);
}

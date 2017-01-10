package com.didekin.retrofit;

import com.didekin.comunidad.Comunidad;

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

    String COMUNIDAD_PATH = "/comunidad";
    String COMUNIDAD_READ = COMUNIDAD_PATH + "/read";
    String COMUNIDAD_SEARCH = UsuarioEndPoints.OPEN + "/comunidad_search";

    @GET(COMUNIDAD_READ + "/{comunidadId}")
    Call<Comunidad> getComuData(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @POST(COMUNIDAD_SEARCH)
    Call<List<Comunidad>> searchComunidades(@Body Comunidad comunidad);
}

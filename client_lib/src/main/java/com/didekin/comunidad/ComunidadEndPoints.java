package com.didekin.comunidad;

import com.didekin.usuariocomunidad.UsuarioComunidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.didekin.usuario.UsuarioEndPoints.OPEN;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 13:33
 */
@SuppressWarnings("unused")
public interface ComunidadEndPoints {

    String COMUNIDAD_PATH = "/comunidad";
    String COMUNIDAD_READ = COMUNIDAD_PATH + "/read";
    String COMUNIDAD_SEARCH = OPEN + "/comunidad_search";
    String USERCOMUS_BY_COMU = COMUNIDAD_READ + "/usercomus_by_comu";

    @GET(COMUNIDAD_READ + "/{comunidadId}")
    Call<Comunidad> getComuData(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @POST(COMUNIDAD_SEARCH)
    Call<List<Comunidad>> searchComunidades(@Body Comunidad comunidad);

    @GET(USERCOMUS_BY_COMU + "/{comunidadId}")
    Call<List<UsuarioComunidad>> seeUserComusByComu(@Header("Authorization") String accessToken,
                                                    @Path("comunidadId") long comunidadId);
}

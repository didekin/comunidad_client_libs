package com.didekinlib.model.relacion.incidencia.http;

import com.didekinlib.model.relacion.incidencia.dominio.ImportanciaUser;
import com.didekinlib.model.relacion.incidencia.dominio.IncidAndResolBundle;
import com.didekinlib.model.relacion.incidencia.dominio.IncidComment;
import com.didekinlib.model.relacion.incidencia.dominio.IncidImportancia;
import com.didekinlib.model.relacion.incidencia.dominio.IncidenciaUser;
import com.didekinlib.model.relacion.incidencia.dominio.Resolucion;

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
 * Date: 12/11/15
 * Time: 17:05
 */

public interface IncidenciaServEndPoints {

    @PUT(IncidServConstant.CLOSE_INCIDENCIA)
    Single<Response<Integer>> closeIncidencia(@Header("Authorization") String accessToken,
                                              @Body Resolucion resolucion);

    @DELETE(IncidServConstant.DELETE_INCID + "/{incidenciaId}")
    Single<Response<Integer>> deleteIncidencia(@Header("Authorization") String accessToken, @Path("incidenciaId") long incidenciaId);

    @PUT(IncidServConstant.MOD_INCID_IMPORTANCIA)
    Single<Response<Integer>> modifyIncidImportancia(@Header("Authorization") String accessToken,
                                                     @Body IncidImportancia incidImportancia);

    @PUT(IncidServConstant.MOD_RESOLUCION)
    Single<Response<Integer>> modifyResolucion(@Header("Authorization") String accessToken,
                                               @Body Resolucion resolucion);

    @POST(IncidServConstant.REG_INCID_COMMENT)
    Single<Response<Integer>> regIncidComment(@Header("Authorization") String accessToken,
                                              @Body IncidComment comment);

    @POST(IncidServConstant.REG_INCID_IMPORTANCIA)
    Single<Response<Integer>> regIncidImportancia(@Header("Authorization") String accessToken,
                                                  @Body IncidImportancia incidImportancia);

    @POST(IncidServConstant.REG_RESOLUCION)
    Single<Response<Integer>> regResolucion(@Header("Authorization") String accessToken,
                                            @Body Resolucion resolucion);

    @GET(IncidServConstant.SEE_INCID_IMPORTANCIA + "/{incidenciaId}")
    Single<Response<IncidAndResolBundle>> seeIncidImportancia(@Header("Authorization") String accessToken, @Path("incidenciaId") long incidenciaId);

    @GET(IncidServConstant.SEE_INCID_COMMENTS + "/{incidenciaId}")
    Single<Response<List<IncidComment>>> seeCommentsByIncid(@Header("Authorization") String accessToken,
                                                            @Path("incidenciaId") long incidenciaId);

    @GET(IncidServConstant.SEE_INCIDS_CLOSED_BY_COMU + "/{comunidadId}")
    Single<Response<List<IncidenciaUser>>> seeIncidsClosedByComu(@Header("Authorization") String accessToken,
                                                                 @Path("comunidadId") long comunidadId);

    @GET(IncidServConstant.SEE_INCIDS_OPEN_BY_COMU + "/{comunidadId}")
    Single<Response<List<IncidenciaUser>>> seeIncidsOpenByComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @GET(IncidServConstant.SEE_RESOLUCION + "/{incidenciaId}")
    Maybe<Response<Resolucion>> seeResolucion(@Header("Authorization") String accessToken, @Path("incidenciaId") long incidenciaId);

    @GET(IncidServConstant.SEE_USERCOMUS_IMPORTANCIA + "/{incidenciaId}")
    Single<Response<List<ImportanciaUser>>> seeUserComusImportancia(@Header("Authorization") String accessToken,
                                                                    @Path("incidenciaId") long incidenciaId);
}

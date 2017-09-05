package com.didekinlib.http.retrofit;

import com.didekinlib.model.incidencia.dominio.ImportanciaUser;
import com.didekinlib.model.incidencia.dominio.IncidAndResolBundle;
import com.didekinlib.model.incidencia.dominio.IncidComment;
import com.didekinlib.model.incidencia.dominio.IncidImportancia;
import com.didekinlib.model.incidencia.dominio.IncidenciaUser;
import com.didekinlib.model.incidencia.dominio.Resolucion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.didekinlib.http.IncidServConstant.CLOSE_INCIDENCIA;
import static com.didekinlib.http.IncidServConstant.DELETE_INCID;
import static com.didekinlib.http.IncidServConstant.MOD_INCID_IMPORTANCIA;
import static com.didekinlib.http.IncidServConstant.MOD_RESOLUCION;
import static com.didekinlib.http.IncidServConstant.REG_INCID_COMMENT;
import static com.didekinlib.http.IncidServConstant.REG_INCID_IMPORTANCIA;
import static com.didekinlib.http.IncidServConstant.REG_RESOLUCION;
import static com.didekinlib.http.IncidServConstant.SEE_INCIDS_CLOSED_BY_COMU;
import static com.didekinlib.http.IncidServConstant.SEE_INCIDS_OPEN_BY_COMU;
import static com.didekinlib.http.IncidServConstant.SEE_INCID_COMMENTS;
import static com.didekinlib.http.IncidServConstant.SEE_INCID_IMPORTANCIA;
import static com.didekinlib.http.IncidServConstant.SEE_RESOLUCION;
import static com.didekinlib.http.IncidServConstant.SEE_USERCOMUS_IMPORTANCIA;

/**
 * User: pedro@didekin
 * Date: 12/11/15
 * Time: 17:05
 */
@SuppressWarnings("unused")
public interface IncidenciaServEndPoints {

    @PUT(CLOSE_INCIDENCIA)
    Call<Integer> closeIncidencia(@Header("Authorization") String accessToken,
                                  @Body Resolucion resolucion);

    @DELETE(DELETE_INCID + "/{incidenciaId}")
    Call<Integer> deleteIncidencia(@Header("Authorization") String accessToken, @Path("incidenciaId") long incidenciaId);

    @PUT(MOD_INCID_IMPORTANCIA)
    Call<Integer> modifyIncidImportancia(@Header("Authorization") String accessToken,
                                         @Body IncidImportancia incidImportancia);

    @PUT(MOD_RESOLUCION)
    Call<Integer> modifyResolucion(@Header("Authorization") String accessToken,
                                   @Body Resolucion resolucion);

    @POST(REG_INCID_COMMENT)
    Call<Integer> regIncidComment(@Header("Authorization") String accessToken,
                                  @Body IncidComment comment);

    @POST(REG_INCID_IMPORTANCIA)
    Call<Integer> regIncidImportancia(@Header("Authorization") String accessToken,
                                      @Body IncidImportancia incidImportancia);

    @POST(REG_RESOLUCION)
    Call<Integer> regResolucion(@Header("Authorization") String accessToken,
                                @Body Resolucion resolucion);

    @GET(SEE_INCID_IMPORTANCIA + "/{incidenciaId}")
    Call<IncidAndResolBundle> seeIncidImportancia(@Header("Authorization") String accessToken, @Path("incidenciaId") long incidenciaId);

    @GET(SEE_INCID_COMMENTS + "/{incidenciaId}")
    Call<List<IncidComment>> seeCommentsByIncid(@Header("Authorization") String accessToken,
                                                @Path("incidenciaId") long incidenciaId);

    @GET(SEE_INCIDS_CLOSED_BY_COMU + "/{comunidadId}")
    Call<List<IncidenciaUser>> seeIncidsClosedByComu(@Header("Authorization") String accessToken,
                                                     @Path("comunidadId") long comunidadId);

    @GET(SEE_INCIDS_OPEN_BY_COMU + "/{comunidadId}")
    Call<List<IncidenciaUser>> seeIncidsOpenByComu(@Header("Authorization") String accessToken, @Path("comunidadId") long comunidadId);

    @GET(SEE_RESOLUCION + "/{incidenciaId}")
    Call<Resolucion> seeResolucion(@Header("Authorization") String accessToken, @Path("incidenciaId") long incidenciaId);

    @GET(SEE_USERCOMUS_IMPORTANCIA + "/{incidenciaId}")
    Call<List<ImportanciaUser>> seeUserComusImportancia(@Header("Authorization") String accessToken,
                                                        @Path("incidenciaId") long incidenciaId);
}

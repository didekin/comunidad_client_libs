package com.didekinlib.model.relacion.incidencia.http;

import com.didekinlib.http.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */

public enum IncidenciaExceptionMsg implements ExceptionMsgIf {

    INCIDENCIA_NOT_FOUND(null, 404),
    INCIDENCIA_NOT_REGISTERED(null, 409),
    INCIDENCIA_USER_WRONG_INIT(null, 412),
    INCID_IMPORTANCIA_NOT_FOUND(null, 404),
    RESOLUCION_DUPLICATE(null, 409), // There exists a resolucion for the same incidencia.
    RESOLUCION_NOT_FOUND(null, 404),;

    private final String httpMsg;
    private final int httpStatus;

    IncidenciaExceptionMsg(String httpMsg, int httpStatus)
    {
        this.httpMsg = httpMsg;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getHttpMessage()
    {
        return httpMsg == null ? name() : httpMsg;
    }

    @Override
    public int getHttpStatus()
    {
        return httpStatus;
    }
}

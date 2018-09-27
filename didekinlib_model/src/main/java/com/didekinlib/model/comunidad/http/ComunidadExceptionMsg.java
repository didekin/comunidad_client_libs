package com.didekinlib.model.comunidad.http;

import com.didekinlib.http.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */

public enum ComunidadExceptionMsg implements ExceptionMsgIf {

    COMUNIDAD_DUPLICATE(null, 409),
    COMUNIDAD_NOT_FOUND(null, 404),;

    private final String httpMsg;
    private final int httpStatus;

    ComunidadExceptionMsg(String httpMsg, int httpStatus)
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

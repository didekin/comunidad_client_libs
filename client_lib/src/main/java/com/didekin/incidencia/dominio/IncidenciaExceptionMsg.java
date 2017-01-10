package com.didekin.incidencia.dominio;

import com.didekin.exception.ExceptionMsg;
import com.didekin.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum IncidenciaExceptionMsg implements ExceptionMsgIf {

    AVANCE_WRONG_INIT(new ExceptionMsg(null, 412)),
    INCIDENCIA_COMMENT_WRONG_INIT(new ExceptionMsg(null, 412)),
    INCIDENCIA_NOT_FOUND(new ExceptionMsg(null, 404)),
    INCIDENCIA_NOT_REGISTERED(new ExceptionMsg(null, 409)),
    INCIDENCIA_USER_WRONG_INIT(new ExceptionMsg(null, 412)),
    INCIDENCIA_WRONG_INIT(new ExceptionMsg(null, 412)),
    INCID_IMPORTANCIA_NOT_FOUND(new ExceptionMsg(null, 404)),
    INCID_IMPORTANCIA_WRONG_INIT(new ExceptionMsg(null, 412)),
    RESOLUCION_DUPLICATE(new ExceptionMsg(null, 409)), // There exists a resolucion for the same incidencia.
    RESOLUCION_NOT_FOUND(new ExceptionMsg(null, 404)),
    RESOLUCION_WRONG_INIT(new ExceptionMsg(null, 412)),;

    private final ExceptionMsgIf msg;

    IncidenciaExceptionMsg(ExceptionMsgIf msg)
    {
        this.msg = msg;
    }

    public String getHttpMessage()
    {
        return msg.getHttpMessage() == null ? name() : msg.getHttpMessage();
    }

    @Override
    public int getHttpStatus()
    {
        return msg.getHttpStatus();
    }
}

package com.didekinlib.http.comunidad;

import com.didekinlib.http.exception.ExceptionMsg;
import com.didekinlib.http.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum ComunidadExceptionMsg implements ExceptionMsgIf {

    COMUNIDAD_NOT_COMPARABLE(new ExceptionMsg(null, 412)),
    COMUNIDAD_DUPLICATE(new ExceptionMsg(null, 409)),
    COMUNIDAD_NOT_FOUND(new ExceptionMsg(null, 404)),
    COMUNIDAD_NOT_HASHABLE(new ExceptionMsg(null, 412)),
    COMUNIDAD_WRONG_INIT(new ExceptionMsg(null, 412)),
    SUFIJO_NUM_IN_COMUNIDAD_NULL(new ExceptionMsg(null, 412)),;

    private final ExceptionMsgIf msg;

    ComunidadExceptionMsg(ExceptionMsgIf msg)
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

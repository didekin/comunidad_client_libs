package com.didekinlib.model.usuariocomunidad;

import com.didekinlib.model.exception.ExceptionMsg;
import com.didekinlib.model.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum UsuarioComunidadExceptionMsg implements ExceptionMsgIf {

    ROLES_NOT_FOUND(new ExceptionMsg(null, 401)),
    USERCOMU_WRONG_INIT(new ExceptionMsg(null, 412)),
    USER_COMU_NOT_FOUND(new ExceptionMsg(null, 404)),;

    private final ExceptionMsgIf msg;

    UsuarioComunidadExceptionMsg(ExceptionMsgIf msg)
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

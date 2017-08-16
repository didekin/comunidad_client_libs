package com.didekinlib.model.usuario;

import com.didekinlib.model.exception.ExceptionMsg;
import com.didekinlib.model.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum UsuarioExceptionMsg implements ExceptionMsgIf {

    PASSWORD_NOT_SENT(new ExceptionMsg("password_not_sent", 500)),
    USER_DATA_NOT_MODIFIED(new ExceptionMsg(null, 409)),
    USER_DATA_NOT_INSERTED(new ExceptionMsg(null, 409)),
    USER_NOT_COMPARABLE(new ExceptionMsg(null, 412)),
    USER_NOT_EQUAL_ABLE(new ExceptionMsg(null, 412)),
    USER_NAME_NOT_FOUND(new ExceptionMsg(null, 404)),
    USER_NAME_DUPLICATE(new ExceptionMsg(null, 409)),
    USER_NOT_HASHABLE(new ExceptionMsg(null, 412)),
    USER_WRONG_INIT(new ExceptionMsg(null, 412)),;

    private final ExceptionMsgIf msg;

    UsuarioExceptionMsg(ExceptionMsgIf msg)
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

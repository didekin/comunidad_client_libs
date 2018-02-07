package com.didekinlib.http.usuario;

import com.didekinlib.http.exception.ExceptionMsg;
import com.didekinlib.http.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum UsuarioExceptionMsg implements ExceptionMsgIf {

    // user
    BAD_REQUEST(new ExceptionMsg("Bad Request", 400)),
    PASSWORD_NOT_SENT(new ExceptionMsg("password_not_sent", 500)),
    TOKEN_NOT_DELETED(new ExceptionMsg(null, 417)),
    TOKEN_NULL(new ExceptionMsg(null, 400)),
    UNAUTHORIZED(new ExceptionMsg("Unauthorized", 401)),
    UNAUTHORIZED_TX_TO_USER(new ExceptionMsg(null, 401)),
    USER_DATA_NOT_MODIFIED(new ExceptionMsg(null, 409)),
    USER_DATA_NOT_INSERTED(new ExceptionMsg(null, 409)),
    USER_NAME_NOT_FOUND(new ExceptionMsg(null, 404)),
    USER_NAME_DUPLICATE(new ExceptionMsg(null, 409)),
    USER_WRONG_INIT(new ExceptionMsg(null, 412)),
    // userComu
    ROLES_NOT_FOUND(new ExceptionMsg(null, 401)),
    USERCOMU_WRONG_INIT(new ExceptionMsg(null, 412)),
    USER_COMU_NOT_FOUND(new ExceptionMsg(null, 404)),
    ;

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

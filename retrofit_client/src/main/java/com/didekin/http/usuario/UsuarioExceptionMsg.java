package com.didekin.http.usuario;

import com.didekin.http.ExceptionMsg;
import com.didekin.http.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum UsuarioExceptionMsg implements ExceptionMsgIf {

    COMUNIDAD_NOT_COMPARABLE(new ExceptionMsg(null, 412)),
    COMUNIDAD_DUPLICATE(new ExceptionMsg(null, 409)),
    COMUNIDAD_NOT_FOUND(new ExceptionMsg(null, 404)),
    COMUNIDAD_NOT_HASHABLE(new ExceptionMsg(null, 412)),
    COMUNIDAD_WRONG_INIT(new ExceptionMsg(null, 412)),
    ROLES_NOT_FOUND(new ExceptionMsg(null, 401)),
    SUFIJO_NUM_IN_COMUNIDAD_NULL(new ExceptionMsg(null, 412)),
    USERCOMU_WRONG_INIT(new ExceptionMsg(null, 412)),
    USER_COMU_NOT_FOUND(new ExceptionMsg(null, 404)),
    USER_DATA_NOT_MODIFIED(new ExceptionMsg(null, 417)),
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

package com.didekinlib.http;

import com.didekinlib.model.exception.ExceptionMsg;
import com.didekinlib.model.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum GenericExceptionMsg implements ExceptionMsgIf {

    BAD_REQUEST(new ExceptionMsg("Bad Request", 400)),
    GENERIC_INTERNAL_ERROR(new ExceptionMsg("Internal Server Error", 500)),
    NOT_FOUND(new ExceptionMsg("Not Found", 404)),
    TOKEN_NOT_DELETED(new ExceptionMsg(null, 417)),
    TOKEN_NULL(new ExceptionMsg(null, 400)),
    UNAUTHORIZED(new ExceptionMsg("Unauthorized", 401)),
    UNAUTHORIZED_TX_TO_USER(new ExceptionMsg(null, 401)),;

    private final ExceptionMsgIf msg;

    GenericExceptionMsg(ExceptionMsgIf msg)
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

package com.didekinlib.http.exception;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
@SuppressWarnings("unused")
public enum GenericExceptionMsg implements ExceptionMsgIf {

    GENERIC_INTERNAL_ERROR(new ExceptionMsg("Internal Server Error", 500)),
    NOT_FOUND(new ExceptionMsg("Not Found", 404)),;

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

package com.didekinlib.http.exception;

/**
 * User: pedro
 * Date: 20/07/15
 * Time: 16:26
 */
@SuppressWarnings("unused")
public class ErrorBean {

    private final String message;
    private final int httpStatus;

    public ErrorBean(String message, int httpStatus)
    {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ErrorBean(ExceptionMsgIf exceptionMsg)
    {
        this(exceptionMsg.getHttpMessage(), exceptionMsg.getHttpStatus());
    }

    public int getHttpStatus()
    {
        return httpStatus;
    }

    public String getMessage()
    {
        return message;
    }
}

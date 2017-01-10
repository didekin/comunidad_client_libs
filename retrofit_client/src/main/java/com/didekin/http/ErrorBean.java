package com.didekin.http;

import static com.didekin.http.GenericExceptionMsg.GENERIC_INTERNAL_ERROR;

/**
 * User: pedro
 * Date: 20/07/15
 * Time: 16:26
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ErrorBean {

    public static final ErrorBean GENERIC_ERROR = new ErrorBean(GENERIC_INTERNAL_ERROR.getHttpMessage(), GENERIC_INTERNAL_ERROR.getHttpStatus());
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

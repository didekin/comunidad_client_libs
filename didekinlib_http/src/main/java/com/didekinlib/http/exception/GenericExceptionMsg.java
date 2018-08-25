package com.didekinlib.http.exception;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */

public enum GenericExceptionMsg implements ExceptionMsgIf {

    GENERIC_INTERNAL_ERROR("Internal Server Error", 500),
    DATABASE_ERROR("Data base server Error", 500),
    NOT_FOUND("Not Found", 404),
    ;

    private final String httpMsg;
    private final int httpStatus;

    GenericExceptionMsg(String httpMsg, int httpStatus)
    {
        this.httpMsg = httpMsg;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getHttpMessage()
    {
        return httpMsg == null ? name() : httpMsg;
    }

    @Override
    public int getHttpStatus()
    {
        return httpStatus;
    }
}

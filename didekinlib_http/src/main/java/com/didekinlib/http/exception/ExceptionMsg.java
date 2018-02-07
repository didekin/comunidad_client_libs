package com.didekinlib.http.exception;

/**
 * User: pedro@didekin
 * Date: 10/01/17
 * Time: 10:55
 */
public class ExceptionMsg implements ExceptionMsgIf {

    private final String httpMessage;
    private final int httpStatus;

    public ExceptionMsg(String httpMessage, int httpStatus)
    {
        this.httpMessage = httpMessage;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getHttpMessage()
    {
        return httpMessage;
    }

    @Override
    public int getHttpStatus()
    {
        return httpStatus;
    }
}

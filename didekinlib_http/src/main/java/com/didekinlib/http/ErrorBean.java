package com.didekinlib.http;

import com.didekinlib.model.exception.ExceptionMsg;
import com.didekinlib.model.exception.ExceptionMsgIf;

/**
 * User: pedro
 * Date: 20/07/15
 * Time: 16:26
 */
@SuppressWarnings("unused")
public class ErrorBean {

    private final ExceptionMsgIf exceptionMsg;

    public ErrorBean(ExceptionMsgIf exceptionMsg)
    {
       this.exceptionMsg = exceptionMsg;
    }

    public ErrorBean(String httpMessage, int httpCode)
    {
        exceptionMsg = new ExceptionMsg(httpMessage, httpCode);
    }

    public int getHttpStatus()
    {
        return exceptionMsg.getHttpStatus();
    }

    public String getMessage()
    {
        return exceptionMsg.getHttpMessage();
    }
}

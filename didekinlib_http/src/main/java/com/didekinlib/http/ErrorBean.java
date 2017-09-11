package com.didekinlib.http;

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

    public int getHttpStatus()
    {
        return exceptionMsg.getHttpStatus();
    }

    public String getMessage()
    {
        return exceptionMsg.getHttpMessage();
    }
}

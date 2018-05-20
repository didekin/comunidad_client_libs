package com.didekinlib.http.auth;

import com.didekinlib.http.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
public enum AuthExceptionMsg implements ExceptionMsgIf {

    TOKEN_ENCRYP_DECRYP_ERROR(null, 401),
    TOKEN_NOT_DELETED(null, 417),
    TOKEN_NULL(null, 400),
    UNAUTHORIZED("Unauthorized", 401),
    ;

    private final String httpMsg;
    private final int httpStatus;

    AuthExceptionMsg(String httpMsg, int httpStatus)
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

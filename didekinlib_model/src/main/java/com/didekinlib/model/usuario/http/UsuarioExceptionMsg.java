package com.didekinlib.model.usuario.http;

import com.didekinlib.http.exception.ExceptionMsgIf;

/**
 * User: pedro@didekin
 * Date: 07/11/15
 * Time: 13:58
 */
public enum UsuarioExceptionMsg implements ExceptionMsgIf {

    BAD_REQUEST("Bad Request", 400),
    FIREBASE_SERVICE_NOT_AVAILABLE("Firebase service not available", 500),
    // user
    TOKEN_ENCRYP_DECRYP_ERROR(null, 401),
    PASSWORD_NOT_SENT("password_not_sent", 500),
    PASSWORD_WRONG("The password does not correspond to the user", 401),
    UNAUTHORIZED("Unauthorized", 401),
    UNAUTHORIZED_TX_TO_USER(null, 401),
    USER_DATA_NOT_MODIFIED(null, 409),
    USER_DATA_NOT_INSERTED(null, 409),
    USER_DUPLICATE(null, 409),
    USER_NOT_FOUND(null, 404),
    USER_WRONG_INIT(null, 412),
    // userComu
    ROLES_NOT_FOUND(null, 401),
    USERCOMU_WRONG_INIT(null, 412),
    USER_COMU_NOT_FOUND(null, 404),
    ;

    private final String httpMsg;
    private final int httpStatus;

    UsuarioExceptionMsg(String httpMsg, int httpStatus)
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

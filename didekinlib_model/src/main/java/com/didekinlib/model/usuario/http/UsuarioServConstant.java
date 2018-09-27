package com.didekinlib.model.usuario.http;

import static com.didekinlib.http.CommonServConstant.OPEN;

/**
 * User: pedro@didekin
 * Date: 30/05/16
 * Time: 16:59
 */
public final class UsuarioServConstant {

    // Code constants.
    public static final int IS_USER_DELETED = -1;
    // Params.
    public static final String AUTH_HEADER = "Authorization";
    public static final String USER_PARAM = "username";
    public static final String PSWD_PARAM = "password";
    public static final String OLD_PSWD_PARAM = "oldPassword";
    public static final String APP_ID_PARAM = "appID";
    // OPEN paths.
    public static final String LOGIN = OPEN + "/login";
    public static final String PASSWORD_SEND = OPEN + "/pswd_send";
    // CLOSED paths.
    public static final String USER_PATH = "/usuario";
    public static final String USER_READ = USER_PATH + "/read";
    public static final String USER_WRITE = USER_PATH + "/write";
    public static final String USER_DELETE = USER_WRITE + "/delete";
    public static final String PASSWORD_MODIFY = USER_WRITE + "/pswd";

    private UsuarioServConstant()
    {
    }
}

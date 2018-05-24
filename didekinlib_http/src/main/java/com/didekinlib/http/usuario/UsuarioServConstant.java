package com.didekinlib.http.usuario;

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
    public static final String APP_ID_PARAM = "appID"; // TODO: cambiar nombre desde GCM_TOKEN_PARAM.
    // OPEN paths.
    public static final String OPEN = "/open";
    public static final String LOGIN = OPEN + "/login";
    public static final String PASSWORD_SEND = OPEN + "/pswd_send";
    // CLOSED paths.
    public static final String USER_PATH = "/usuario";
    public static final String USER_READ = USER_PATH + "/read";
    public static final String USER_WRITE = USER_PATH + "/write";
    public static final String USER_WRITE_GCM_TOKEN = USER_WRITE + "/gcm_token";
    public static final String USER_DELETE = USER_WRITE + "/delete";
    public static final String PASSWORD_MODIFY = USER_WRITE + "/pswd";



    private UsuarioServConstant()
    {
    }
}

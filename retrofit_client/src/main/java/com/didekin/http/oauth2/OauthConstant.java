package com.didekin.http.oauth2;

import com.didekin.usuariocomunidad.Rol;

/**
 * User: pedro@didekin
 * Date: 04/09/15
 * Time: 11:02
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class OauthConstant {

    // Oauth2: Grant types.
    public static final String PASSWORD_GRANT = "password";
    public static final String REFRESH_TOKEN_GRANT = "refresh_token";
    // Oauth2: token scope.
    public static final String READ_WRITE_SCOPE = "readwrite";
    // Oauth2: token type.
    public static final String BEARER_TOKEN_TYPE = "bearer";

    /*Paths used by the authorization server*/
    public static final String TOKEN_PATH = "/oauth/token";
    public static final String REFRESH_TK_PARAM = "refresh_token";
    public static final String CLIENT_ID_PARAM = "client_id";
    public static final String GRANT_TYPE_PARAM = "grant_type";
    /*Http basic authentication realm.*/
    public static final String USERS_RSRC_ID = "users_didekin";

    /*Authorities. They are based on Rol enum class.*/
    public static final String ADMON_AUTH = Rol.ADMINISTRADOR.authority;
    public static final String USER_AUTH = Rol.PROPIETARIO.authority;

    private OauthConstant()
    {
    }
}



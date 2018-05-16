package com.didekinlib.http.auth;

/**
 * User: pedro@didekin
 * Date: 04/09/15
 * Time: 11:02
 */

public final class AuthConstant {

    public static final String TOKEN_PATH = "/auth/token";
    public static final String PK_TOKEN_PATH = TOKEN_PATH + "/pk"; // TODO: delete?
    public static final String AUTH_HEADER = "Authorization";

    private AuthConstant()
    {
    }
}



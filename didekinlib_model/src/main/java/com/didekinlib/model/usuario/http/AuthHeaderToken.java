package com.didekinlib.model.usuario.http;

import static com.didekinlib.model.usuario.http.TkValidaPatterns.error_token_from_jsonStr;
import static com.didekinlib.model.usuario.http.TkValidaPatterns.tkEncrypted_direct_symmetricKey_REGEX;

/**
 * User: pedro@didekin
 * Date: 21/05/2018
 * Time: 11:42
 */
public final class AuthHeaderToken {

    private final String token;

    public AuthHeaderToken(String tokenIn)
    {
        if (tokenIn == null || !tkEncrypted_direct_symmetricKey_REGEX.isPatternOk(tokenIn)) {
            throw new IllegalArgumentException(error_token_from_jsonStr + this.getClass().getName());
        }
        token = tokenIn;
    }

    public String getToken()
    {
        return token;
    }
}

package com.didekinlib.model.usuario.http;

import com.didekinlib.BeanBuilder;
import com.google.gson.Gson;

import static com.didekinlib.model.usuario.http.TkValidaPatterns.error_tokenInLocal;
import static com.didekinlib.model.usuario.http.TkValidaPatterns.tkEncrypted_direct_symmetricKey_REGEX;
import static java.util.Base64.getUrlDecoder;
import static java.util.Base64.getUrlEncoder;

/**
 * User: pedro@didekin
 * Date: 21/05/2018
 * Time: 11:42
 */
public class AuthHeader implements AuthHeaderIf {

    private final String token;

    private AuthHeader(AuthHeaderBuilder builder)
    {
        token = builder.authHeaderToken;
    }

    @Override
    public String toJsonString()
    {
        return new Gson().toJson(this);
    }

    @Override
    public String toBase64Str()
    {
        return getUrlEncoder().encodeToString(toJsonString().getBytes());
    }

    @Override
    public String getToken()
    {
        return token;
    }

    //    ==================== BUILDER ====================

    public static class AuthHeaderBuilder implements BeanBuilder<AuthHeaderIf> {

        private String authHeaderToken;


        public AuthHeaderBuilder()
        {
        }

        /**
         * Initializer for consumers of tokens.
         */
        public AuthHeaderBuilder tokenFromJsonBase64Header(String jsonBase64Token)
        {
            AuthHeaderIf header = new Gson().fromJson(new String(getUrlDecoder().decode(jsonBase64Token)), AuthHeader.class);
            tokenInDb(header.getToken());
            return this;
        }

        /**
         * Initializer for producers of tokens.
         */
        public AuthHeaderBuilder tokenInDb(String tokenInDb)
        {
            if (tokenInDb != null && tkEncrypted_direct_symmetricKey_REGEX.isPatternOk(tokenInDb)) {
                authHeaderToken = tokenInDb;
                return this;
            }
            throw new IllegalArgumentException(error_tokenInLocal + this.getClass().getName());
        }

        @Override
        public AuthHeaderIf build()
        {
            AuthHeader header = new AuthHeader(this);
            if (header.token == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return header;
        }
    }
}

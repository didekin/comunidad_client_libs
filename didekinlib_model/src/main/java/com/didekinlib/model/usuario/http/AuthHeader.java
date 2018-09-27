package com.didekinlib.model.usuario.http;

import com.didekinlib.model.common.dominio.BeanBuilder;
import com.google.gson.Gson;

import static com.didekinlib.model.usuario.http.TkValidaPatterns.error_tokenInLocal;
import static com.didekinlib.model.usuario.http.TkValidaPatterns.tkEncrypted_direct_symmetricKey_REGEX;
import static com.didekinlib.model.common.dominio.ValidDataPatterns.error_appId;
import static java.util.Base64.getUrlDecoder;
import static java.util.Base64.getUrlEncoder;

/**
 * User: pedro@didekin
 * Date: 21/05/2018
 * Time: 11:42
 */
public class AuthHeader implements AuthHeaderIf {

    private final String appID;
    private final String token;

    private AuthHeader(AuthHeaderBuilder builder)
    {
        appID = builder.appID;
        token = builder.tokenInLocal;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

    @Override
    public String getBase64Str()
    {
        return getUrlEncoder().encodeToString(toString().getBytes());
    }

    @Override
    public String getAppID()
    {
        return appID;
    }

    @Override
    public String getToken()
    {
        return token;
    }

    //    ==================== BUILDER ====================

    public static class AuthHeaderBuilder implements BeanBuilder<AuthHeaderIf> {

        private String appID;
        private String tokenInLocal;

        /**
         * Constructor for producers of encoded headers, as client apps.
         */
        public AuthHeaderBuilder()
        {
        }

        /**
         * Constructor for consumers of encoded headers, as http server applications.
         */
        public AuthHeaderBuilder(String base64HeaderIn)
        {
            this();
            AuthHeaderIf header = new Gson().fromJson(
                    new String(getUrlDecoder().decode(base64HeaderIn)),
                    AuthHeader.class
            );
            appId(header.getAppID()).tokenInLocal(header.getToken());
        }

        public AuthHeaderBuilder appId(String appIdIn)
        {
            if (appIdIn != null && !appIdIn.isEmpty()) {
                appID = appIdIn;
                return this;
            }
            throw new IllegalArgumentException(error_appId + this.getClass().getName());
        }

        public AuthHeaderBuilder tokenInLocal(String tokenInLocalIn)
        {
            if (tkEncrypted_direct_symmetricKey_REGEX.isPatternOk(tokenInLocalIn)) {
                tokenInLocal = tokenInLocalIn;
                return this;
            }
            throw new IllegalArgumentException(error_tokenInLocal + this.getClass().getName());
        }

        @Override
        public AuthHeaderIf build()
        {
            AuthHeader header = new AuthHeader(this);
            if (header.appID == null || header.token == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return header;
        }
    }
}

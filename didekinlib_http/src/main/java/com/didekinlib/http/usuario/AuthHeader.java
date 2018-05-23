package com.didekinlib.http.usuario;

import com.didekinlib.model.common.dominio.BeanBuilder;
import com.google.gson.Gson;

import org.jose4j.base64url.Base64Url;

import static com.didekinlib.http.usuario.TkValidaPatterns.error_tokenInLocal;
import static com.didekinlib.http.usuario.TkValidaPatterns.tkEncrypted_direct_symmetricKey_REGEX;
import static com.didekinlib.model.common.dominio.ValidDataPatterns.EMAIL;
import static com.didekinlib.model.common.dominio.ValidDataPatterns.error_appId;
import static com.didekinlib.model.common.dominio.ValidDataPatterns.error_userName;
import static org.jose4j.lang.StringUtil.UTF_8;

/**
 * User: pedro@didekin
 * Date: 21/05/2018
 * Time: 11:42
 */
public class AuthHeader {

    private final String userName;
    private final String appID;
    private final String token;

    private AuthHeader(AuthHeaderBuilder builder)
    {
        userName = builder.userName;
        appID = builder.appID;
        token = builder.tokenInLocal;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

    public String getBase64Str()
    {
        return new Base64Url().base64UrlEncode(toString(), UTF_8);
    }

    public String getUserName()
    {
        return userName;
    }

    public String getAppID()
    {
        return appID;
    }

    public String getToken()
    {
        return token;
    }

    //    ==================== BUILDER ====================

    public static class AuthHeaderBuilder implements BeanBuilder<AuthHeader> {

        private String userName;
        private String appID;
        private String tokenInLocal;

        /**
         *  Constructor for producers of encoded headers, as client apps.
         */
        public AuthHeaderBuilder()
        {
        }

        /**
         *  Constructor for consumers of encoded headers, as http server applications.
         */
        public AuthHeaderBuilder(String base64HeaderIn)
        {
            this();
            AuthHeader header = new Gson().fromJson(
                    new Base64Url().base64UrlDecodeToUtf8String(base64HeaderIn),
                    AuthHeader.class
            );
            appId(header.getAppID()).userName(header.getUserName()).tokenInLocal(header.getToken());
        }

        public AuthHeaderBuilder appId(String appIdIn)
        {
            if (appIdIn != null && !appIdIn.isEmpty()) {
                appID = appIdIn;
                return this;
            }
            throw new IllegalArgumentException(error_appId + this.getClass().getName());
        }

        public AuthHeaderBuilder userName(String userNameIn)
        {
            if (EMAIL.isPatternOk(userNameIn)) {
                userName = userNameIn;
                return this;
            }
            throw new IllegalArgumentException(error_userName + this.getClass().getName());
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
        public AuthHeader build()
        {
            AuthHeader header = new AuthHeader(this);
            if (header.userName == null || header.appID == null || header.token == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return header;
        }
    }
}

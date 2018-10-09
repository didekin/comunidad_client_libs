package com.didekinlib.model.usuario.http;

import com.didekinlib.BeanBuilder;
import com.google.gson.Gson;

import io.reactivex.functions.Function;

import static com.didekinlib.model.usuario.http.TkValidaPatterns.error_tokenFromJsonBase64Header;
import static com.didekinlib.model.usuario.http.TkValidaPatterns.error_tokenInDb;
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
    private final Base64Supplier base64Supplier;

    private AuthHeader(AuthHeaderBuilder builder)
    {
        token = builder.authHeaderToken;
        base64Supplier = builder.base64Supplier;
    }

    @Override
    public String toJsonString()
    {
        return new Gson().toJson(token);
    }

    @Override
    public String toBase64JsonStr()
    {
        try {
            return base64Supplier.getEncoderFunction().apply(toJsonString());
        } catch (Exception e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    @Override
    public String getToken()
    {
        return token;
    }

    //    ==================== BUILDER ====================

    public interface Base64Supplier {
        default Function<String, String> getDecoderFunction()
        {
            return (String s) -> new String(getUrlDecoder().decode(s));
        }

        default Function<String, String> getEncoderFunction()
        {
            return (String s) -> getUrlEncoder().encodeToString(s.getBytes());
        }
    }

    public static class AuthHeaderBuilder implements BeanBuilder<AuthHeaderIf> {

        private String authHeaderToken;
        private Base64Supplier base64Supplier;

        public AuthHeaderBuilder()
        {
            base64Supplier = new Base64Supplier() {
            };
        }

        public AuthHeaderBuilder(Base64Supplier base64SupplierIn)
        {
            base64Supplier = base64SupplierIn;
        }

        /**
         * Initializer for consumers of tokens.
         */
        public AuthHeaderBuilder tokenFromJsonBase64Header(String jsonBase64Token)
        {
            try {
                AuthHeaderIf header = new Gson().fromJson(base64Supplier.getDecoderFunction().apply(jsonBase64Token), AuthHeader.class);
                tokenInDb(header.getToken());
            } catch (Exception e) {
                throw new IllegalArgumentException(error_tokenFromJsonBase64Header + this.getClass().getName());
            }
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
            throw new IllegalArgumentException(error_tokenInDb + this.getClass().getName());
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

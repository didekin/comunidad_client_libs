package com.didekinlib.http.auth;

import static com.didekinlib.http.auth.AuthConstant.BEARER_TOKEN_TYPE;

/**
 * User: pedro@didekin
 * Date: 27/04/15
 * Time: 13:50
 */
@SuppressWarnings("unused")
public enum AuthClient {

    CL_USER("user", ""),
    CL_ADMON("admon", ""),
    TEST1("test_one", ""),;

    private String id;
    private String secret;

    AuthClient(String id, String secret)
    {
        this.id = id;
        this.secret = secret;
    }

    public String getId()
    {
        return id;
    }

    public String getSecret()
    {
        return secret;
    }

    // ================== Static helpers ======================

    public static final String BASIC_AND_SPACE = "Basic ";

    public static String doBearerAccessTkHeader(SpringOauthToken springOauthToken)
    {
        return BEARER_TOKEN_TYPE.substring(0, 1).toUpperCase() + BEARER_TOKEN_TYPE.substring(1)
                + " " + springOauthToken.getValue();
    }
}

package com.didekinlib.http.usuario;

import org.jose4j.base64url.Base64Url;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 21/05/2018
 * Time: 12:40
 */
public class AuthHeaderTest {

    private static final String tokenInLocal = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0" +
            "." +
            "._L86WbOFHY-3g0E2EXejJg" +
            ".UB1tHZZq0TYFTZKPVZXY83GRxHz770Aq7BuMCEbNnaSC5cVNOLEOgBQrOQVJmVL-9Ke9KRSwuq7MmVcA2EB_0xRBr_YbzmMWbpUcTQUFtE5OZOFiCsxL5Yn0gA_DDLZboivpoSqndQRP-44mWVkM1A" +
            ".RIvTWRrsyoJ1mpl8vUhQDQ";

    private static final String userName = "pedro@didekin.com";
    private static final String appId = "cVNOLEOgBQrOQVJmVL-9Ke9KRSw..uq:7MmVcA2EB_0xRBr";

    private AuthHeader header;

    @Before
    public void setUp()
    {
        header = new AuthHeader.AuthHeaderBuilder()
                .appId(appId)
                .userName(userName)
                .tokenInLocal(tokenInLocal)
                .build();
    }

    @Test
    public void test_ToString()
    {
        assertThat(header.toString(), allOf(
                containsString("\"userName\"" + ":" + "\"" + userName + "\""),
                containsString("\"appID\"" + ":" + "\"" + appId + "\""),
                containsString("\"token\"" + ":" + "\"" + tokenInLocal + "\""),
                containsString("{"),
                containsString("}")
        ));
    }

    @Test
    public void test_GetBase64Str()
    {
        String headerBase64 = header.getBase64Str();
        System.out.printf("%s%n", headerBase64);
        assertThat(new Base64Url().base64UrlDecodeToUtf8String(headerBase64), is(header.toString()));
    }

    @Test
    public void test_HeaderFromBase64Str()
    {
        AuthHeader headerPojo = new AuthHeader.AuthHeaderBuilder(header.getBase64Str()).build();
        assertThat(headerPojo, allOf
                (
                        hasProperty("userName", allOf(
                                equalTo(header.getUserName()),
                                equalTo(userName))),
                        hasProperty("appID", allOf(
                                equalTo(header.getAppID()),
                                equalTo(appId))),
                        hasProperty("token", allOf(
                                equalTo(header.getToken()),
                                equalTo(tokenInLocal)))
                )
        );
    }
}
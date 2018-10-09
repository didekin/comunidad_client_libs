package com.didekinlib.model.usuario.http;

import org.junit.Before;
import org.junit.Test;

import static java.util.Base64.getUrlDecoder;
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

    private AuthHeaderIf header;

    @Before
    public void setUp()
    {
        header = new AuthHeader.AuthHeaderBuilder()
                .tokenInDb(tokenInLocal)
                .build();
    }

    @Test
    public void test_toJsonString()
    {
        assertThat(header.toJsonString(), allOf(
                containsString("\"token\"" + ":" + "\"" + tokenInLocal + "\""),
                containsString("{"),
                containsString("}")
        ));
    }

    @Test
    public void test_toBase64FromJsonStr()
    {
        String headerBase64 = header.toBase64JsonStr();
        System.out.printf("%s%n", headerBase64);
        assertThat(new String(getUrlDecoder().decode(headerBase64)), is(header.toJsonString()));
    }

    @Test
    public void test_tokenFromJsonBase64Header()
    {
        AuthHeaderIf headerPojo = new AuthHeader.AuthHeaderBuilder().tokenFromJsonBase64Header(header.toBase64JsonStr()).build();
        assertThat(headerPojo, hasProperty("token", allOf(equalTo(header.getToken()), equalTo(tokenInLocal))));
    }
}
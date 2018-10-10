package com.didekinlib.model.usuario.http;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 21/05/2018
 * Time: 12:40
 */
public class AuthHeaderTokenTest {

    private static final String tokenInLocal =
            "eyJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0" +
                    "." +
                    "._L86WbOFHY-3g0E2EXejJg" +
                    ".UB1tHZZq0TYFTZKPVZXY83GRxHz770Aq7BuMCEbNnaSC5cVNOLEOgBQrOQVJmVL-9Ke9KRSwuq7MmVcA2EB_0xRBr_YbzmMWbpUcTQUFtE5OZOFiCsxL5Yn0gA_DDLZboivpoSqndQRP-44mWVkM1A" +
                    ".RIvTWRrsyoJ1mpl8vUhQDQ";

    @Test
    public void test_getToken()
    {
        AuthHeaderToken header = new AuthHeaderToken(tokenInLocal);
        assertThat(header.getToken(), is(tokenInLocal));
    }
}
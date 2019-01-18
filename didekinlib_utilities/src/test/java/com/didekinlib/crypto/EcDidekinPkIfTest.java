package com.didekinlib.crypto;

import org.junit.Test;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 2019-01-17
 * Time: 13:05
 */
public class EcDidekinPkIfTest {

    @Test
    public void test_GetSha256Base64EcX() throws NoSuchAlgorithmException
    {
        byte[] sha256B64 = new EcDidekinPkStored(new BigInteger("1000"), new BigInteger("2111")).getSha256Base64EcX();
        assertThat(sha256B64.length > 32 && sha256B64.length < 64, is(true));
        assertThat(new String(sha256B64).length(), is(sha256B64.length));

        sha256B64 = new EcDidekinKey.EcDidekinPk(new EcDidekinKey().getEcDidekinPk()).getSha256Base64EcX();
        assertThat(sha256B64.length > 32 && sha256B64.length < 64, is(true));
        assertThat(new String(sha256B64).length(), is(sha256B64.length));
    }
}
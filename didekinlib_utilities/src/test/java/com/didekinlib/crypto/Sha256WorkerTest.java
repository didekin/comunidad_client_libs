package com.didekinlib.crypto;

import org.junit.Test;

import java.util.Base64;

import static java.util.Base64.getEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 16/12/2018
 * Time: 11:52
 */
public class Sha256WorkerTest {

    private static final byte[] payLoad = "PAyload__bytes".getBytes();
    private Sha256Worker worker = new Sha256Worker();

    @Test
    public void test_HashSha256()
    {
        assertThat(worker.hashSha256(payLoad).length, is(32));
    }

    @Test
    public void test_HashSha256Base64()
    {
        final byte[] hashSha256Base64 = worker.hashSha256Base64(payLoad);
        assertThat(hashSha256Base64, is(getEncoder().encode(worker.hashSha256(payLoad))));
        assertThat(hashSha256Base64.length > 32, is(true));
    }

    @Test
    public void test_VerifyHashSha256()
    {
        assertThat(worker.verifySha256(payLoad, worker.hashSha256(payLoad)), is(true));
    }

    @Test
    public void test_VerifySha256Base64()
    {
        assertThat(worker.verifySha256Base64(Base64.getEncoder().encode(payLoad), worker.hashSha256(payLoad)), is(true));
    }
}
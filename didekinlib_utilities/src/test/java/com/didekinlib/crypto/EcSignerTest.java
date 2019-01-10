package com.didekinlib.crypto;

import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Arrays;
import java.util.Base64;

import static com.didekinlib.crypto.EcDidekinKey.getKeyPair;
import static java.util.Base64.getEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 2018-12-18
 * Time: 13:08
 */
public class EcSignerTest {

    @Test
    public void test_SignMsg()
    {
        KeyPair keyPair = getKeyPair();
        final byte[] msgToSign = "........ Long message for test".getBytes();
        EcSigner ecSigner = new EcSigner(new EcDidekinSk((ECPrivateKey) keyPair.getPrivate()), msgToSign);
        byte[] signedMsg = ecSigner.signMsg();
        assertThat(signedMsg.length >= 64, is(true));
        System.out.printf("Signature Base 64: %s", Arrays.toString(Base64.getEncoder().encode(signedMsg)));

        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        EcVerifier verifier = new EcVerifier(msgToSign,
                getEncoder().encode(signedMsg),
                new EcDidekinPk(publicKey.getW().getAffineX(), publicKey.getW().getAffineY()));
        assertThat(verifier.verifySignedMsg(), is(true));
    }
}
package com.didekinlib.crypto;

import org.junit.Test;

import java.security.interfaces.ECPublicKey;
import java.util.Base64;

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
        final byte[] msgToSign = "........ Long message for test".getBytes();
        EcDidekinKey ecDidekinKey = new EcDidekinKey();

        // Sign.
        EcSigner ecSigner = new EcSigner(new EcDidekinKey.EcDidekinSk(ecDidekinKey.getEcDidekinSk()), msgToSign);
        byte[] signedMsg = ecSigner.signMsg();
        assertThat(signedMsg.length >= 64, is(true));

        // Verify.
        ECPublicKey publicKey = ecDidekinKey.getEcDidekinPk();
        final EcDidekinPkStored ecDidekinPkStored = new EcDidekinPkStored(publicKey.getW().getAffineX(), publicKey.getW().getAffineY());

        byte[] base64EncodBytes = Base64.getEncoder().encode(signedMsg);
        String base64encondedStr = new String(base64EncodBytes);
        System.out.printf("Signature Base 64: %s, length: %d", base64encondedStr, base64encondedStr.length());

        EcVerifier verifier = new EcVerifier(msgToSign, base64EncodBytes, ecDidekinPkStored);
        assertThat(verifier.verifySignedMsg(), is(true));
    }
}
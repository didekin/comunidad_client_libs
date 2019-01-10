package com.didekinlib.crypto;

import org.junit.Test;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

import static com.didekinlib.crypto.EcDidekinKey.getKeyPair;
import static com.didekinlib.crypto.EcSignatureConfig.alg_for_signing_keys;
import static com.didekinlib.crypto.EcSignatureConfig.ec_public_key_format;
import static com.didekinlib.crypto.EcSignatureConfig.signing_key_size;
import static java.util.Base64.getEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 10/12/2018
 * Time: 13:48
 */
public class EcVerifierTest {

    private static final byte[] msgToSign = "Hola Pedro".getBytes();

    @Test
    public void test_VerifySignedMsg()
    {
        KeyPair keyPair = getKeyPair();
        EcSigner signer = new EcSigner(new EcDidekinSk((ECPrivateKey) keyPair.getPrivate()), msgToSign);
        byte[] sig = signer.signMsg();
        // Encoded signed message.
        byte[] sigBase64 = getEncoder().encode(sig);

        ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
        EcDidekinPk pk = new EcDidekinPk(ecPublicKey.getW().getAffineX(), ecPublicKey.getW().getAffineY());

        assertThat(pk.getAlgorithm(), is(alg_for_signing_keys));
        assertThat(pk.getFormat(), is(ec_public_key_format));
        assertThat(pk.getParams().getCurve().getField().getFieldSize(), is(signing_key_size));

        EcVerifier verifier = new EcVerifier(msgToSign, sigBase64, pk);
        assertThat(verifier.verifySignedMsg(), is(true));
    }
}
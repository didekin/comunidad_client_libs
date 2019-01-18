package com.didekinlib.crypto;

import org.junit.Test;

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
        EcDidekinKey ecDidekinKey = new EcDidekinKey();
        EcSigner signer = new EcSigner(new EcDidekinKey.EcDidekinSk(ecDidekinKey.getEcDidekinSk()), msgToSign);
        byte[] sig = signer.signMsg();
        // Encoded signed message.
        byte[] sigBase64 = getEncoder().encode(sig);

        EcDidekinKey.EcDidekinPk ecDidekinPk = new EcDidekinKey.EcDidekinPk(ecDidekinKey.getEcDidekinPk());
        EcDidekinPkIf pk = new EcDidekinPkStored(ecDidekinPk.getW().getAffineX(), ecDidekinPk.getW().getAffineY());

        EcVerifier verifier = new EcVerifier(msgToSign, sigBase64, pk);
        assertThat(verifier.verifySignedMsg(), is(true));
    }
}
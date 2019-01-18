package com.didekinlib.crypto;

import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;

import static com.didekinlib.crypto.EcSignatureProvider.signatureProvider;


/**
 * User: pedro@didekin
 * Date: 10/12/2018
 * Time: 12:16
 * <p>
 *
 * Elliptic curve: secp256r1 [NIST P-256, X9.62 prime256v1]
 */
public class EcSigner implements Signer {

    private final EcDidekinKey.EcDidekinSk privateKey;
    private final byte[] msgToSign;
    private final Signature signature;

    public EcSigner(EcDidekinKey.EcDidekinSk privateKey, byte[] msgToSign)
    {
        this.privateKey = privateKey;
        this.msgToSign = msgToSign;
        signature = signatureProvider.getSignature();
    }

    @Override
    public EcDidekinKey.EcDidekinSk getPrivateKey()
    {
        return privateKey;
    }

    @Override
    public byte[] getMsgToSign()
    {
        return msgToSign;
    }

    @Override
    public byte[] signMsg()
    {
        try {
            signature.initSign(privateKey);
            signature.update(msgToSign);
            return signature.sign();
        } catch (InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

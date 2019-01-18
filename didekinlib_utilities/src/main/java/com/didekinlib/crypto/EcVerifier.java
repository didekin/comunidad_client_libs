package com.didekinlib.crypto;

import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;

import static com.didekinlib.crypto.EcSignatureProvider.signatureProvider;
import static java.util.Base64.getDecoder;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 17:42
 */
public class EcVerifier implements SignatureVerifier<EcDidekinPkIf> {

    /**
     * Base64 encoded byte array.
     */
    private final byte[] signedMsg;
    private final EcDidekinPkIf publicKey;
    private final byte[] msgToVerify;
    private final Signature signature;

    public EcVerifier(byte[] msgToVerify, byte[] signedMsg, EcDidekinPkIf publicKey)
    {
        this.msgToVerify = msgToVerify;
        this.signedMsg = signedMsg;
        this.publicKey = publicKey;
        signature = signatureProvider.getSignature();
    }

    @Override
    public byte[] getSignedMsg()
    {
        return signedMsg;
    }

    @Override
    public EcDidekinPkIf getPublicKey()
    {
        return publicKey;
    }

    @Override
    public byte[] getMsgToVerify()
    {
        return msgToVerify;
    }

    @Override
    public boolean verifySignedMsg()
    {
        try {
            signature.initVerify(publicKey);
            signature.update(msgToVerify);
            return signature.verify(getDecoder().decode(signedMsg));
        } catch (InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

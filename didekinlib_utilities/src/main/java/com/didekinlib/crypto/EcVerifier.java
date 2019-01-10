package com.didekinlib.crypto;

import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;

import static com.didekinlib.crypto.EcSignature.getSignature;
import static java.util.Base64.getDecoder;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 17:42
 */
public class EcVerifier implements SignatureVerifier {

    /**
     * Base64 encoded byte array.
     */
    private final byte[] signedMsg;
    private final EcDidekinPk publicKey;
    private final byte[] msgToVerify;
    private static final Signature signature = getSignature();

    public EcVerifier(byte[] msgToVerify, byte[] signedMsg, EcDidekinPk publicKey)
    {
        this.msgToVerify = msgToVerify;
        this.signedMsg = signedMsg;
        this.publicKey = publicKey;
    }

    @Override
    public byte[] getSignedMsg()
    {
        return signedMsg;
    }

    @Override
    public EcDidekinPk getPublicKey()
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

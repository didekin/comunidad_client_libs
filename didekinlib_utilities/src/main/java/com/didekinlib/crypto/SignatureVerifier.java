package com.didekinlib.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 17:43
 */
public interface SignatureVerifier<E extends PublicKey> {

    byte[] getSignedMsg();

     E getPublicKey();

    byte[] getMsgToVerify();

    boolean verifySignedMsg() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}

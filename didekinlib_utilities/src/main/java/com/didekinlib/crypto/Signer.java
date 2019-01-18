package com.didekinlib.crypto;

import java.security.InvalidKeyException;
import java.security.SignatureException;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 17:41
 */
public interface Signer {

    EcDidekinKey.EcDidekinSk getPrivateKey();

    byte[] getMsgToSign();

    byte[] signMsg() throws InvalidKeyException, SignatureException;
}

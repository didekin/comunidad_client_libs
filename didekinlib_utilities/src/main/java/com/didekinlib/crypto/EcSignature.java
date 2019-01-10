package com.didekinlib.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import static com.didekinlib.crypto.EcSignatureConfig.alg_for_signing;
import static java.security.Signature.getInstance;

/**
 * User: pedro@didekin
 * Date: 2018-12-18
 * Time: 11:01
 */
public interface EcSignature {

    static Signature getSignature()
    {
        try {
            return getInstance(alg_for_signing);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

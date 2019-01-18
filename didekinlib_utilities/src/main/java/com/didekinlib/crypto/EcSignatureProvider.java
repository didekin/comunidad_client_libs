package com.didekinlib.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import static com.didekinlib.crypto.EcSignatureConfig.ecdsa_sha256_sign_alg;
import static java.security.Signature.getInstance;

/**
 * User: pedro@didekin
 * Date: 2018-12-18
 * Time: 11:01
 */
public final class EcSignatureProvider {

    public static final EcSignatureProvider signatureProvider = new EcSignatureProvider();
    private final Signature signature;

    private EcSignatureProvider()
    {
        signature = getSignatureInstance();
    }

    private static Signature getSignatureInstance()
    {
        try {
            return getInstance(ecdsa_sha256_sign_alg);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Signature getSignature()
    {
        return signature;
    }
}

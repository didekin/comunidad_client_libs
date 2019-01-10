package com.didekinlib.crypto;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECKey;

import static com.didekinlib.crypto.EcSignatureConfig.alg_for_signing_keys;
import static com.didekinlib.crypto.EcSignatureConfig.ec_public_key_format;
import static com.didekinlib.crypto.EcSignatureConfig.signing_key_size;
import static java.security.KeyPairGenerator.getInstance;
import static java.util.Objects.requireNonNull;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 18:59
 */
public interface EcDidekinKey extends ECKey, Key {

    default String getAlgorithm()
    {
        return alg_for_signing_keys;
    }

    default String getFormat()
    {
        return ec_public_key_format;
    }

    default byte[] getEncoded()
    {
        throw new UnsupportedOperationException();
    }

    static KeyPair getKeyPair()
    {
        try {
            KeyPairGenerator keyGen = getInstance(alg_for_signing_keys);
            requireNonNull(keyGen).initialize(signing_key_size);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

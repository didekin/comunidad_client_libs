package com.didekinlib.crypto;

import java.security.Key;

import static com.didekinlib.crypto.EcSignatureConfig.ec_keys_generation_alg;
import static com.didekinlib.crypto.EcSignatureConfig.ec_public_key_format;
import static java.security.KeyPairGenerator.getInstance;
import static java.util.Objects.requireNonNull;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 18:59
 */
public interface EcDidekinKeyIf extends Key {

    default String getAlgorithm()
    {
        return ec_keys_generation_alg;
    }

    default String getFormat()
    {
        return ec_public_key_format;
    }

    default byte[] getEncoded()
    {
        throw new UnsupportedOperationException();
    }
}

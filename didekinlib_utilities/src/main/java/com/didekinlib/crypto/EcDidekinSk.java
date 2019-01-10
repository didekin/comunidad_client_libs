package com.didekinlib.crypto;

import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;

import static com.didekinlib.crypto.EcDidekinKey.getKeyPair;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 13:44
 */
public class EcDidekinSk implements ECPrivateKey, EcDidekinKey {

    private final ECPrivateKey sk;

    EcDidekinSk(ECPrivateKey ecPrivateKeyIn)
    {
        sk = ecPrivateKeyIn;
    }

    @Override
    public BigInteger getS()
    {
        return sk.getS();
    }

    @Override
    public ECParameterSpec getParams()
    {
        return ((ECPrivateKey) getKeyPair().getPrivate()).getParams();
    }
}

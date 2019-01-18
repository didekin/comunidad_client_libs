package com.didekinlib.crypto;

import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;

import static com.didekinlib.crypto.EcDidekinKey.keyGenerator;

/**
 * User: pedro@didekin
 * Date: 2019-01-16
 * Time: 17:23
 */
public class EcDidekinPkStored implements EcDidekinPkIf {

    private final ECPoint ecPoint;
    private static final ECParameterSpec ecParamsPk = ((ECPublicKey) keyGenerator.generateKeyPair().getPublic()).getParams();

    public EcDidekinPkStored(BigInteger ecXIn, BigInteger ecYIn)
    {
        ecPoint = new ECPoint(ecXIn, ecYIn);
    }

    @Override
    public ECPoint getW()
    {
        return ecPoint;
    }

    @Override
    public ECParameterSpec getParams()
    {
        return ecParamsPk;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj)
    {
        return equalsEcDidekinPk(obj);
    }

    @Override
    public int hashCode()
    {
        return hashCodeEcDidekinPk();
    }
}

package com.didekinlib.crypto;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;

import static com.didekinlib.crypto.EcDidekinKey.getKeyPair;
import static java.util.Base64.getEncoder;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 13:44
 */
public class EcDidekinPk implements ECPublicKey, EcDidekinKey {

    private final BigInteger ecX;
    private final BigInteger ecY;
    private final byte[] sha_ecX;

    public EcDidekinPk(BigInteger ecX, BigInteger ecY)
    {
        try {
            this.ecX = ecX;
            this.ecY = ecY;
            sha_ecX = new Sha256Worker().hashSha256Base64(ecX.toByteArray());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] getSha256Base64EcX()
    {
        return getEncoder().encode(sha_ecX);
    }

    public String getEcXstr()
    {
        return ecX.toString(10);
    }

    public String getEcYstr()
    {
        return ecY.toString(10);
    }

    @Override
    public ECPoint getW()
    {
        return new ECPoint(ecX, ecY);
    }

    @Override
    public ECParameterSpec getParams()
    {
        return ((ECPublicKey) getKeyPair().getPublic()).getParams();
    }
}

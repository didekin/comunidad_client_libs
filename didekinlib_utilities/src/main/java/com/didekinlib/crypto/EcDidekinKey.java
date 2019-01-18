package com.didekinlib.crypto;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;

import static com.didekinlib.crypto.EcSignatureConfig.ec_key_size;
import static com.didekinlib.crypto.EcSignatureConfig.ec_keys_generation_alg;
import static java.security.KeyPairGenerator.getInstance;
import static java.util.Objects.requireNonNull;

/**
 * User: pedro@didekin
 * Date: 2019-01-16
 * Time: 16:35
 */
public class EcDidekinKey implements EcDidekinKeyIf {

    private final EcDidekinPk ecDidekinPk;
    private final EcDidekinSk ecDidekinSk;
    static final KeyPairGenerator keyGenerator = getKeyGenerator();

    static KeyPairGenerator getKeyGenerator()
    {
        try {
            KeyPairGenerator keyGen = getInstance(ec_keys_generation_alg);
            requireNonNull(keyGen).initialize(ec_key_size);
            return keyGen;
        } catch (NullPointerException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public EcDidekinKey()
    {
        KeyPair keyPair = keyGenerator.generateKeyPair();
        ecDidekinPk = new EcDidekinPk((ECPublicKey) keyPair.getPublic());
        ecDidekinSk = new EcDidekinSk((ECPrivateKey) keyPair.getPrivate());
    }

    public ECPublicKey getEcDidekinPk()
    {
        return ecDidekinPk;
    }

    public ECPrivateKey getEcDidekinSk()
    {
        return ecDidekinSk;
    }

    // =========================  INNER CLASSES ==========================

    public static class EcDidekinSk implements ECPrivateKey, EcDidekinKeyIf {

        private final ECPrivateKey ecPrivateKey;

        public EcDidekinSk(ECPrivateKey ecPrivateKey)
        {
            this.ecPrivateKey = ecPrivateKey;
        }

        @Override
        public BigInteger getS()
        {
            return ecPrivateKey.getS();
        }

        @Override
        public ECParameterSpec getParams()
        {
            return ecPrivateKey.getParams();
        }
    }

    public static class EcDidekinPk implements EcDidekinPkIf {

        private final ECPublicKey ecPublicKey;

        public EcDidekinPk(ECPublicKey ecPublicKey)
        {
            this.ecPublicKey = ecPublicKey;
        }

        @Override
        public ECPoint getW()
        {
            return ecPublicKey.getW();
        }

        @Override
        public ECParameterSpec getParams()
        {
            return ecPublicKey.getParams();
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
}

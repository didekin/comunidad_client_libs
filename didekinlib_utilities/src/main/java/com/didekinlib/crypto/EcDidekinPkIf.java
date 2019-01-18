package com.didekinlib.crypto;

import java.security.interfaces.ECPublicKey;
import java.util.Arrays;

/**
 * User: pedro@didekin
 * Date: 2019-01-16
 * Time: 18:17
 */
public interface EcDidekinPkIf extends ECPublicKey, EcDidekinKeyIf {

    default byte[] getSha256Base64EcX()
    {
        return new Sha256Worker().hashSha256Base64(getW().getAffineX().toByteArray());
    }

    default String getEcXstr()
    {
        return getW().getAffineX().toString(10);
    }

    default String getEcYstr()
    {
        return getW().getAffineY().toString(10);
    }

    default boolean equalsEcDidekinPk(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EcDidekinPkIf pk = (EcDidekinPkIf) obj;
        return Arrays.equals(pk.getSha256Base64EcX(), getSha256Base64EcX());
    }

    default int hashCodeEcDidekinPk()
    {
        return Arrays.hashCode(getSha256Base64EcX());
    }
}

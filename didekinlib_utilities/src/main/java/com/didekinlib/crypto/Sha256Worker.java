package com.didekinlib.crypto;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import static java.security.MessageDigest.getInstance;
import static java.util.Base64.getEncoder;

/**
 * User: pedro@didekin
 * Date: 14/12/2018
 * Time: 20:05
 */
public class Sha256Worker {

    public byte[] hashSha256(byte[] bytesPayload)
    {
        try {
            return getInstance("SHA-256").digest(bytesPayload);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] hashSha256Base64(byte[] bytesPayload)
    {
        return getEncoder().encode(hashSha256(bytesPayload));
    }

    public boolean verifySha256(byte[] payload, byte[] hashSha256Bytes)
    {
        return Arrays.equals(hashSha256(payload), hashSha256Bytes);
    }

    public boolean verifySha256Base64(byte[] base64Payload, byte[] hashSha256Bytes)
    {
        return verifySha256(Base64.getDecoder().decode(base64Payload), hashSha256Bytes);
    }
}

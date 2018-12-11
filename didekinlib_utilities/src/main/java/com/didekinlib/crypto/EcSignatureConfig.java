package com.didekinlib.crypto;

/**
 * User: pedro@didekin
 * Date: 10/12/2018
 * Time: 14:34
 * <p>
 * Specification: secp256r1 [NIST P-256]
 */
public final class EcSignatureConfig {

    public static final String ec_public_key_format = "X.509";
    public static final String ec_curve_name = "secp256r1";
    public static final String alg_for_signing = "SHA256withECDSA";
    public static final int signing_key_size = 256;
    public static final String alg_for_signing_keys = "EC";

    private EcSignatureConfig()
    {
    }
}

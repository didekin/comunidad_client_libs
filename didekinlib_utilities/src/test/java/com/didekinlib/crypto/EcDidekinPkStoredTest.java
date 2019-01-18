package com.didekinlib.crypto;

import org.junit.Test;

import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;

import static com.didekinlib.crypto.EcDidekinKey.keyGenerator;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 2019-01-16
 * Time: 15:14
 */
public class EcDidekinPkStoredTest {

    @Test
    public void test_GetParams()
    {
        EcDidekinPkStored ecDidekinPkStored = new EcDidekinPkStored(new BigInteger("1222"), new BigInteger("2111"));
        ECParameterSpec thisParams = ecDidekinPkStored.getParams();
        ECParameterSpec staticParams = ((ECPublicKey) keyGenerator.generateKeyPair().getPublic()).getParams();

        assertThat(thisParams.getCurve().equals(staticParams.getCurve()), is(true));
        assertThat(thisParams.getGenerator().equals(staticParams.getGenerator()), is(true));
        assertThat(thisParams.getOrder().equals(staticParams.getOrder()), is(true));
        assertThat(thisParams.getCofactor(), is(staticParams.getCofactor()));
    }
}
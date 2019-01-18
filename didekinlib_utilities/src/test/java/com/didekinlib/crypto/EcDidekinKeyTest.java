package com.didekinlib.crypto;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 2019-01-16
 * Time: 14:54
 */
public class EcDidekinKeyTest {

    @Test
    public void test_GetKeyGenerator()
    {
        assertThat(EcDidekinKey.getKeyGenerator(), notNullValue());

        EcDidekinKey ecDidekinKey = new EcDidekinKey();
        assertThat(ecDidekinKey.getEcDidekinPk(), notNullValue());
        assertThat(ecDidekinKey.getEcDidekinSk(), notNullValue());
    }

    @Test
    public void test_EcDidekinPk()
    {
        EcDidekinKey.EcDidekinPk ecDidekinPk = new EcDidekinKey.EcDidekinPk(new EcDidekinKey().getEcDidekinPk());
        assertThat(ecDidekinPk.getParams(), notNullValue());
        assertThat(ecDidekinPk.getW(), notNullValue());
    }
}
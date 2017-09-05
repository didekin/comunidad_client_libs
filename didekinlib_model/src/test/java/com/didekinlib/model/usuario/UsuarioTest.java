package com.didekinlib.model.usuario;

import org.junit.Test;

/**
 * User: pedro@didekin
 * Date: 05/09/17
 * Time: 13:10
 */
public class UsuarioTest {

    /**
     * if usuario.uId == 0 && usuario.userName == null
     * throws IllegalStateException.
     */
    @Test(expected = IllegalStateException.class)
    public void testBuild_1()
    {
        new Usuario.UsuarioBuilder().build();
    }
}
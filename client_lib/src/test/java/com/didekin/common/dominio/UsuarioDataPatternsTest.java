package com.didekin.common.dominio;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: pedro@didekin
 * Date: 07/11/16
 * Time: 12:36
 */
public class UsuarioDataPatternsTest {

    @Test
    public void testAliasPattern() throws Exception
    {
        assertThat(UsuarioDataPatterns.ALIAS.isPatternOk("pedro nevado"), is(true));
        assertThat(UsuarioDataPatterns.ALIAS.isPatternOk("pedro nevado raja"), is(true));
        assertThat(UsuarioDataPatterns.ALIAS.isPatternOk("pedro nevado +"), is(false));
    }
}
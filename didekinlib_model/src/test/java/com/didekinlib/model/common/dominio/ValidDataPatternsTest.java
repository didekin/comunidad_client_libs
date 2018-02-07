package com.didekinlib.model.common.dominio;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: pedro@didekin
 * Date: 07/11/16
 * Time: 12:36
 */
public class ValidDataPatternsTest {

    @Test
    public void testAliasPattern()
    {
        assertThat(ValidDataPatterns.ALIAS.isPatternOk("pedro nevado"), is(true));
        assertThat(ValidDataPatterns.ALIAS.isPatternOk("pedro nevado raja"), is(true));
        assertThat(ValidDataPatterns.ALIAS.isPatternOk("pedro nevado +"), is(false));
    }
}
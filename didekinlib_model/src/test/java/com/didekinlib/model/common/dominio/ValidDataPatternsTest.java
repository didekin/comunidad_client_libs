package com.didekinlib.model.common.dominio;

import org.junit.Test;

import static com.didekinlib.model.common.dominio.ValidDataPatterns.ALIAS;
import static com.didekinlib.model.common.dominio.ValidDataPatterns.EMAIL;
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
        assertThat(ALIAS.isPatternOk("pedro nevado"), is(true));
        assertThat(ALIAS.isPatternOk("pedro nevado raja"), is(true));
        assertThat(ALIAS.isPatternOk("pedro nevado +"), is(false));
    }

    @Test
    public void testEmailPattern()
    {
        assertThat(EMAIL.isPatternOk("pedro@local+hola@didekin.es"), is(true));
        assertThat(EMAIL.isPatternOk("pedro++--__hola$$@didekin.es"), is(false));
        assertThat(EMAIL.isPatternOk("pedro@@@local+hola@dideaaaaaaaaaaaaaaaaaaabbbbbbccccccdddddkkkkeekslalakjjkin.es"), is(true));
    }
}
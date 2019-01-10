package com.didekinlib.model.common;

import org.junit.Test;

import static com.didekinlib.model.common.ValidDataPatterns.ALIAS;
import static com.didekinlib.model.common.ValidDataPatterns.CIF;
import static com.didekinlib.model.common.ValidDataPatterns.CIF_COMUNIDAD;
import static com.didekinlib.model.common.ValidDataPatterns.EMAIL;
import static com.didekinlib.model.common.ValidDataPatterns.NIE;
import static com.didekinlib.model.common.ValidDataPatterns.NIF;
import static com.didekinlib.model.common.ValidDataPatterns.getCifDigitCtrl;
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

    @Test  // "[0-9]{6,8}[a-zA-Z]"
    public void testNIF_isPatternOk()
    {
        assertThat(NIF.isPatternOk("12345H"), is(false));
        assertThat(NIF.isPatternOk("1234567"), is(false));
        assertThat(NIF.isPatternOk("123456789"), is(false));
        assertThat(NIF.isPatternOk("1234567HZ"), is(false));
        assertThat(NIF.isPatternOk("1234567$"), is(false));
        assertThat(NIF.isPatternOk("0123456S"), is(true));
        assertThat(NIF.isPatternOk("00123456S"), is(true));
        assertThat(NIF.isPatternOk("0012345V"), is(true));
        assertThat(NIF.isPatternOk("00012345V"), is(true));
        assertThat(NIF.isPatternOk("12345678Z"), is(true));
        assertThat(NIF.isPatternOk("12345678z"), is(true));

    }

    @Test  // "[X-Zx-z][0-9]{7}[a-zA-Z]"
    public void testNIE_isPatternOk()
    {
        assertThat(NIE.isPatternOk("X12345678"), is(false));
        assertThat(NIE.isPatternOk("H1234567"), is(false));
        assertThat(NIE.isPatternOk("Hx1234567"), is(false));
        assertThat(NIE.isPatternOk("X12345678"), is(false));
        assertThat(NIE.isPatternOk("X123456zF"), is(false));
        assertThat(NIE.isPatternOk("Z1234567R"), is(true));
        assertThat(NIE.isPatternOk("z1234567r"), is(true));
    }

    @Test  // "[[A-H][J-N][P-S]UVW][0-9]{7}[0-9A-J]"
    public void testCIF_isPatternOk()
    {
        assertThat(CIF.isPatternOk("I1234567A"), is(false));
        assertThat(CIF.isPatternOk("O1234567A"), is(false));
        assertThat(CIF.isPatternOk("61234567A"), is(false));
        assertThat(CIF.isPatternOk("A1234567W"), is(false));

        assertThat(CIF.isPatternOk("Q7850003J"), is(true));
        assertThat(CIF.isPatternOk("N7850003J"), is(true));
        assertThat(CIF.isPatternOk("N78500030"), is(true));
        assertThat(CIF.isPatternOk("B85635910"), is(true));

        assertThat(CIF.isPatternOk("A58818501"), is(true));
        assertThat(CIF.isPatternOk("A5881850A"), is(false));

        assertThat(CIF.isPatternOk("F5410279C"), is(true));
        assertThat(CIF.isPatternOk("L8916088A"), is(true));
        assertThat(CIF.isPatternOk("K8517292B"), is(true));
        assertThat(CIF.isPatternOk("M2871106G"), is(true));


    }

    @Test  // "H[0-9]{7}[0-9]"
    public void testCIF_COMUNIDAD_isPatternOk()
    {
        assertThat(CIF.isPatternOk("A1234567W"), is(false));
        assertThat(CIF_COMUNIDAD.isPatternOk("H1234567W"), is(false));
        assertThat(CIF_COMUNIDAD.isPatternOk("H00012674"), is(true));
        assertThat(CIF_COMUNIDAD.isPatternOk("H76543214"), is(true));
        assertThat(CIF_COMUNIDAD.isPatternOk("H86543212"), is(true));

        assertThat(CIF_COMUNIDAD.isPatternOk("H96543210"), is(true));
        assertThat(CIF_COMUNIDAD.isPatternOk("H76543297"), is(true));

        assertThat(CIF_COMUNIDAD.isPatternOk("H76543289"), is(true));
        assertThat(CIF_COMUNIDAD.isPatternOk("H76543271"), is(true));
        assertThat(CIF_COMUNIDAD.isPatternOk("H76543263"), is(true));
        assertThat(CIF_COMUNIDAD.isPatternOk("H76543511"), is(true));
    }

    @Test
    public void testCIF_getCifDigitCtrl()
    {
        assertThat(getCifDigitCtrl("5881850"), is(1));
        assertThat(getCifDigitCtrl("7850003"), is(0));
    }
}
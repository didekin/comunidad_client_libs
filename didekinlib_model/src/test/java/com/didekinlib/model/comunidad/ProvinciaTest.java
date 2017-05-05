package com.didekinlib.model.comunidad;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * User: pedro@didekin
 * Date: 05/05/17
 * Time: 18:54
 */
public class ProvinciaTest {
    @Test
    public void test_toString() throws Exception
    {
        Provincia provincia = new Provincia((short)45, "provincia_45");
        assertThat(provincia.getNombre(), is(provincia.toString()));
    }

}
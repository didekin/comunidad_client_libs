package com.didekinlib.model.entidad;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 05/05/17
 * Time: 18:52
 */
public class MunicipioTest {

    @Test
    public void test_toString()
    {
        Municipio municipio = new Municipio((short) 12, "municipio_12", new Provincia((short) 4, "provincia_4"));
        assertThat(municipio.getNombre(), is(municipio.toString()));
    }

    @Test
    public void testCompareTo()
    {
        Municipio municipio_1 = new Municipio((short) 23, new Provincia((short) 11));
        Municipio municipio_2 = new Municipio((short) 2, new Provincia((short) 11));
        Municipio municipio_3 = new Municipio((short) 11, new Provincia((short) 9));

        assertThat(municipio_1.compareTo(municipio_2) > 0, is(true));
        assertThat(municipio_2.compareTo(municipio_3) > 0, is(true));
        assertThat(municipio_1.compareTo(municipio_3) > 0, is(true));
    }
}
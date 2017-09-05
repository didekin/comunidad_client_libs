package com.didekinlib.model.comunidad;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * User: pedro@didekin
 * Date: 05/05/17
 * Time: 18:52
 */
public class MunicipioTest {

    @Test
    public void test_toString() throws Exception
    {
       Municipio municipio = new Municipio((short) 12, "municipio_12", new Provincia((short) 4, "provincia_4"));
       assertThat(municipio.getNombre(), is(municipio.toString()));
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testCompareTo() throws Exception
    {
        Municipio municipio_1 = new Municipio((short) 23,new Provincia((short)11));
        Municipio municipio_2 = new Municipio((short) 2,new Provincia((short)11));
        Municipio municipio_3 = new Municipio((short) 11,new Provincia((short)9));

        assertThat((municipio_1.equals(municipio_1)) == (municipio_1.compareTo(municipio_1) == 0), is(true));
        assertThat(municipio_1.compareTo(municipio_2), is(1));
        assertThat(municipio_2.compareTo(municipio_3), is(1));
        assertThat(municipio_1.compareTo(municipio_3), is(1));
    }
}
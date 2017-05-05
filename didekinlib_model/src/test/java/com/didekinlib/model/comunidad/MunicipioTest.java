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
}
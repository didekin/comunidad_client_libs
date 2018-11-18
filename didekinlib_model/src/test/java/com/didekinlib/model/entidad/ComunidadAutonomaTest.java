package com.didekinlib.model.entidad;

import com.didekinlib.model.entidad.ComunidadAutonoma;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 05/05/17
 * Time: 18:49
 */
public class ComunidadAutonomaTest {

    @Test
    public void test_toString()
    {
        ComunidadAutonoma comunidadAutonoma = new ComunidadAutonoma((short) 1, "comunidad_1");
        assertThat(comunidadAutonoma.getNombre(), is(comunidadAutonoma.toString()));
    }
}
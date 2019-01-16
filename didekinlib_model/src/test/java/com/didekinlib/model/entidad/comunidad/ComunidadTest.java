package com.didekinlib.model.entidad.comunidad;

import com.didekinlib.model.entidad.Entidad;

import org.junit.Test;

import static com.didekinlib.model.common.DataUtil.domicilio_0;
import static com.didekinlib.model.common.DataUtil.domicilio_1;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 05/09/17
 * Time: 13:09
 */
public class ComunidadTest {

    @Test
    public void testCompareTo_1()
    {
        Comunidad comunidad_1 = new Comunidad(new Entidad.EntidadBuilder<CifComunidad>().domicilio(domicilio_1));
        Comunidad comunidad_2 = new Comunidad(new Entidad.EntidadBuilder<CifComunidad>().domicilio(domicilio_0));
        assertThat(comunidad_1.compareTo(comunidad_2), is(domicilio_1.compareTo(domicilio_0)));
    }
}
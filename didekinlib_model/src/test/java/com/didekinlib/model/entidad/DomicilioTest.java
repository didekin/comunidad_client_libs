package com.didekinlib.model.entidad;

import org.junit.Test;

import static com.didekinlib.model.common.DataUtil.municipio_1;
import static com.didekinlib.model.common.DataUtil.municipio_2;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 18/11/2018
 * Time: 12:59
 */
public class DomicilioTest {

    @Test
    public void testCompareTo_1()
    {
        Domicilio domicilio_1 = new Domicilio.DomicilioBuilder().tipoVia("tipo1")
                .nombreVia("nombreA")
                .numero((short) 2)
                .sufijoNumero("A")
                .municipio(municipio_1).build();

        Domicilio domicilio_2 = new Domicilio.DomicilioBuilder().tipoVia("tipo1")
                .nombreVia("nombreA")
                .numero((short) 2)
                .municipio(municipio_1).build();

        Domicilio domicilio_3 = new Domicilio.DomicilioBuilder().tipoVia("tipo1")
                .nombreVia("nombreA")
                .numero((short) 2)
                .sufijoNumero("A")
                .municipio(municipio_2).build();

        Domicilio domicilio_4 = new Domicilio.DomicilioBuilder().tipoVia("tipo1")
                .nombreVia("nombreA")
                .numero((short) 2)
                .sufijoNumero("b")
                .municipio(municipio_2).build();

        Domicilio domicilio_5 = new Domicilio.DomicilioBuilder().tipoVia("tipo1")
                .nombreVia("nombreB")
                .numero((short) 2)
                .sufijoNumero("b")
                .municipio(municipio_2).build();

        Domicilio domicilio_6 = new Domicilio.DomicilioBuilder().tipoVia("tipo2")
                .nombreVia("nombreB")
                .numero((short) 2)
                .sufijoNumero("b")
                .municipio(municipio_2).build();

        Domicilio domicilio_2B = new Domicilio.DomicilioBuilder().tipoVia("tipo1")
                .nombreVia("nombreA")
                .numero((short) 2)
                .municipio(municipio_1).build();

        // Sufijo null en ambos domicilios.
        assertThat(domicilio_2.compareTo(domicilio_2B), is(0));
        // Sufijo null en domicilio2.
        assertThat(domicilio_1.compareTo(domicilio_2), is(1));

        assertThat(domicilio_2.compareTo(domicilio_1) < 0, is(true));
        assertThat(domicilio_1.compareTo(domicilio_3) < 0, is(true));
        assertThat(domicilio_1.compareTo(domicilio_4) < 0, is(true));
        assertThat(domicilio_1.compareTo(domicilio_5) < 0, is(true));
        assertThat(domicilio_1.compareTo(domicilio_6) < 0, is(true));
    }
}
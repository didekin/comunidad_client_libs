package com.didekinlib.model.entidad.proveedor;

import com.didekinlib.model.entidad.Cif;
import com.didekinlib.model.entidad.IdFiscal;
import com.didekinlib.model.entidad.Nie;
import com.didekinlib.model.entidad.Nif;

import org.junit.Test;

import static com.didekinlib.model.common.DataUtil.cif_ok;
import static com.didekinlib.model.common.DataUtil.domicilio_1;
import static com.didekinlib.model.common.DataUtil.nif_ok;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 18/11/2018
 * Time: 13:04
 */
public class ProveedorTest {

    final Proveedor<Cif> proveedor_1 = new Proveedor.ProveedorBuilder<Cif>().domicilio(domicilio_1).nombreComercial("B_NOMBRE").build();
    final Proveedor<Nif> proveedor_2 = new Proveedor.ProveedorBuilder<Nif>().domicilio(domicilio_1).nombreComercial("A_NOMBRE").build();
    final Proveedor<Nie> proveedor_3 = new Proveedor.ProveedorBuilder<Nie>().domicilio(domicilio_1).nombreComercial("C_NOMBRE").build();

    @Test
    public void test_CompareTo()
    {
        assertThat(proveedor_1.compareTo(proveedor_2) > 0, is(true));
        assertThat(proveedor_3.compareTo(proveedor_2) > 0, is(true));
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    public void test_Equals()
    {
        assertThat(proveedor_1.equals(proveedor_2), is(false));

        final Proveedor<? extends IdFiscal> proveedor_4 = new Proveedor.ProveedorBuilder<>()
                .domicilio(domicilio_1)
                .nombreComercial("B_NOMBRE")
                .idFiscal(cif_ok).build();

        final Proveedor<? extends IdFiscal> proveedor_5 = new Proveedor.ProveedorBuilder<>()
                .domicilio(domicilio_1)
                .nombreComercial("B_NOMBRE")
                .idFiscal(nif_ok).build();

        assertThat(proveedor_4.equals(proveedor_5), is(false));
    }
}
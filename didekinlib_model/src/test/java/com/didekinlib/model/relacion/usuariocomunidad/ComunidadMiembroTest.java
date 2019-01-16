package com.didekinlib.model.relacion.usuariocomunidad;

import com.didekinlib.model.entidad.Entidad;
import com.didekinlib.model.entidad.comunidad.CifComunidad;
import com.didekinlib.model.entidad.comunidad.Comunidad;
import com.didekinlib.model.usuario.Usuario;

import org.junit.Test;

import static com.didekinlib.model.common.DataUtil.domicilio_1;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 31/08/17
 * Time: 18:15
 */
public class ComunidadMiembroTest {

    @Test
    public void testCompareTo()
    {
        Usuario usuario = new Usuario.UsuarioBuilder().userName("username").build();
        Comunidad comunidad = new Comunidad(new Entidad.EntidadBuilder<CifComunidad>().domicilio(domicilio_1));

        ComunidadMiembro comunidad_Miembro_1 = new ComunidadMiembro.ComuMiembroBuilder(comunidad, usuario)
                .portal("portalA")
                .escalera("escaleraA")
                .planta("plantaB")
                .puerta("puertaA").build();

        ComunidadMiembro comunidad_Miembro_2 = new ComunidadMiembro.ComuMiembroBuilder(comunidad, usuario).build();

        ComunidadMiembro comunidad_Miembro_3 = new ComunidadMiembro.ComuMiembroBuilder(comunidad, usuario)
                .portal("portalB")
                .build();

        ComunidadMiembro comunidad_Miembro_4 = new ComunidadMiembro.ComuMiembroBuilder(comunidad, usuario)
                .escalera("escaleraB")
                .build();

        ComunidadMiembro comunidad_Miembro_5 = new ComunidadMiembro.ComuMiembroBuilder(comunidad, usuario)
                .planta("plantaA")
                .build();

        ComunidadMiembro comunidad_Miembro_6 = new ComunidadMiembro.ComuMiembroBuilder(comunidad, usuario)
                .portal("portalA")
                .build();

        assertThat(comunidad_Miembro_1.compareTo(comunidad_Miembro_2), is(0));
        assertThat(comunidad_Miembro_1.compareTo(comunidad_Miembro_3), is(-1));
        assertThat(comunidad_Miembro_1.compareTo(comunidad_Miembro_4), is(-1));
        assertThat(comunidad_Miembro_1.compareTo(comunidad_Miembro_5), is(1));
        assertThat(comunidad_Miembro_1.compareTo(comunidad_Miembro_6), is(0));
    }

    private ComunidadMiembro doUserComu(String roles)
    {
        return new ComunidadMiembro.ComuMiembroBuilder(
                new Comunidad(new Entidad.EntidadBuilder<CifComunidad>().eId(1L)),
                new Usuario.UsuarioBuilder().userName("userName1").build()
        ).build();
    }
}
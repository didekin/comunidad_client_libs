package com.didekinlib.model.relacion.usuariocomunidad;

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
public class UsuarioComunidadTest {

    @Test
    public void testCompareTo()
    {
        Usuario usuario = new Usuario.UsuarioBuilder().userName("username").build();
        Comunidad comunidad = new Comunidad.ComunidadBuilder().domicilio(domicilio_1).build();

        UsuarioComunidad usuarioComunidad_1 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portalA")
                .escalera("escaleraA")
                .planta("plantaB")
                .puerta("puertaA").build();

        UsuarioComunidad usuarioComunidad_2 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario).build();

        UsuarioComunidad usuarioComunidad_3 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portalB")
                .build();

        UsuarioComunidad usuarioComunidad_4 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .escalera("escaleraB")
                .build();

        UsuarioComunidad usuarioComunidad_5 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .planta("plantaA")
                .build();

        UsuarioComunidad usuarioComunidad_6 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portalA")
                .build();

        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_2), is(0));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_3), is(-1));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_4), is(-1));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_5), is(1));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_6), is(0));
    }

    private UsuarioComunidad doUserComu(String roles)
    {
        return new UsuarioComunidad.UserComuBuilder(
                new Comunidad.ComunidadBuilder().c_id(1L).build(),
                new Usuario.UsuarioBuilder().userName("userName1").build()
        ).build();
    }
}
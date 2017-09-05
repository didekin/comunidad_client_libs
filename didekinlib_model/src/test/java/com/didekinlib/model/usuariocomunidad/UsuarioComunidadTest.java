package com.didekinlib.model.usuariocomunidad;

import com.didekinlib.model.comunidad.Comunidad;
import com.didekinlib.model.comunidad.Municipio;
import com.didekinlib.model.comunidad.Provincia;
import com.didekinlib.model.usuario.Usuario;

import org.junit.Test;

import static com.didekinlib.model.usuariocomunidad.Rol.ADMINISTRADOR;
import static com.didekinlib.model.usuariocomunidad.Rol.INQUILINO;
import static com.didekinlib.model.usuariocomunidad.Rol.PRESIDENTE;
import static com.didekinlib.model.usuariocomunidad.Rol.PROPIETARIO;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 31/08/17
 * Time: 18:15
 */
public class UsuarioComunidadTest {

    @Test
    public void test_HasAdministradorAuthority() throws Exception
    {
        assertThat(doUserComu("adm, inq").hasAdministradorAuthority(), is(true));
        assertThat(doUserComu("pro, inq").hasAdministradorAuthority(), is(false));
    }

    @Test
    public void test_GetHighestRolFunction() throws Exception
    {
        assertThat(doUserComu("pre, inq").getHighestRolFunction(), is("pre"));
        assertThat(doUserComu("adm, pro").getHighestRolFunction(), is("adm"));
        assertThat(doUserComu("inq").getHighestRolFunction(), is("inq"));
    }

    @Test
    public void testCompareTo() throws Exception
    {
        Usuario usuario = new Usuario.UsuarioBuilder().userName("username").build();
        Municipio municipio_1 = new Municipio((short) 23, new Provincia((short) 11));
        Comunidad comunidad = new Comunidad.ComunidadBuilder().tipoVia("tipo1")
                .nombreVia("nombreA")
                .numero((short) 2)
                .sufijoNumero("A")
                .municipio(municipio_1).build();

        UsuarioComunidad usuarioComunidad_1 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portalA")
                .escalera("escaleraA")
                .planta("plantaB")
                .puerta("puertaA")
                .roles(PROPIETARIO.function).build();

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
                .roles(ADMINISTRADOR.function)
                .build();

        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_2), is(0));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_3), is(-1));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_4), is(-1));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_5), is(1));
        assertThat(usuarioComunidad_1.compareTo(usuarioComunidad_6), is(0));
    }

    @Test
    public void testHasRoleAdministrador()
    {
        Usuario usuario = new Usuario.UsuarioBuilder().userName("username").build();
        Municipio municipio_1 = new Municipio((short) 23, new Provincia((short) 11));
        Comunidad comunidad = new Comunidad.ComunidadBuilder()
                .tipoVia("tipo1")
                .nombreVia("nombreA")
                .numero((short) 2)
                .municipio(municipio_1).build();

        UsuarioComunidad usuarioComunidad_1 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portalA")
                .roles(INQUILINO.function)
                .build();
        assertThat(usuarioComunidad_1.hasAdministradorAuthority(), is(false));

        usuarioComunidad_1 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portal B")
                .roles(ADMINISTRADOR.function.concat(",").concat(INQUILINO.function))
                .build();
        assertThat(usuarioComunidad_1.hasAdministradorAuthority(), is(true));

        usuarioComunidad_1 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portal B")
                .roles(PROPIETARIO.function)
                .build();
        assertThat(usuarioComunidad_1.hasAdministradorAuthority(), is(false));

        usuarioComunidad_1 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portal B")
                .roles(PROPIETARIO.function.concat(",").concat(INQUILINO.function))
                .build();
        assertThat(usuarioComunidad_1.hasAdministradorAuthority(), is(false));

        usuarioComunidad_1 = new UsuarioComunidad.UserComuBuilder(comunidad, usuario)
                .portal("portal B")
                .roles(ADMINISTRADOR.function.concat(",")
                        .concat(PRESIDENTE.function).concat(",")
                        .concat(INQUILINO.function))
                .build();
        assertThat(usuarioComunidad_1.hasAdministradorAuthority(), is(true));
    }

    private UsuarioComunidad doUserComu(String roles)
    {
        return new UsuarioComunidad.UserComuBuilder(
                new Comunidad.ComunidadBuilder().c_id(1L).build(),
                new Usuario.UsuarioBuilder().userName("userName1").build()
        ).roles(roles).build();
    }
}
package com.didekinlib.model.usuariocomunidad;

import com.didekinlib.model.comunidad.Comunidad;
import com.didekinlib.model.usuario.Usuario;

import org.junit.Test;

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

    private UsuarioComunidad doUserComu(String roles)
    {
        return new UsuarioComunidad.UserComuBuilder(
                new Comunidad.ComunidadBuilder().c_id(1L).build(),
                new Usuario.UsuarioBuilder().userName("userName1").build()
        ).roles(roles).build();
    }
}
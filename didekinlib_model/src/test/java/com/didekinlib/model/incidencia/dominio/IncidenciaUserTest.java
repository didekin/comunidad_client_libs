package com.didekinlib.model.incidencia.dominio;

import com.didekinlib.model.comunidad.Comunidad;
import com.didekinlib.model.usuario.Usuario;

import org.junit.Test;

import static com.didekinlib.model.incidencia.dominio.IncidenciaExceptionMsg.INCIDENCIA_USER_WRONG_INIT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * User: pedro@didekin
 * Date: 03/09/17
 * Time: 18:23
 */
public class IncidenciaUserTest {

    @Test
    public void test_InvariantUserName() throws Exception
    {
        IncidenciaUser incidenciaUser = doIncidUser("userName1", "userName1");
        assertThat(incidenciaUser, notNullValue());
        assertThat(incidenciaUser.getIncidencia().getUserName().equals(incidenciaUser.getUsuario().getUserName()), is(true));

        try {
            doIncidUser("userName2", "userName3");
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), is(INCIDENCIA_USER_WRONG_INIT.toString()));
        }
    }

    private IncidenciaUser doIncidUser(String userNameIncidencia, String userNameUsuario)
    {
        Incidencia incidencia = new Incidencia.IncidenciaBuilder()
                .incidenciaId(1L)
                .comunidad(new Comunidad.ComunidadBuilder().c_id(1L).build())
                .userName(userNameIncidencia)
                .build();
        return new IncidenciaUser.IncidenciaUserBuilder(incidencia)
                .usuario(new Usuario.UsuarioBuilder().uId(99L).userName(userNameUsuario).build())
                .build();
    }
}
package com.didekinlib.http.usuario;

import org.junit.Test;

import static com.didekinlib.http.incidencia.IncidServConstant.MOD_RESOLUCION;
import static com.didekinlib.http.incidencia.IncidServConstant.SEE_INCIDS_OPEN_BY_COMU;
import static com.didekinlib.http.usuario.TkValidaPatterns.closed_paths_REGEX;
import static com.didekinlib.http.usuario.TkValidaPatterns.tkEncrypted_direct_symmetricKey_REGEX;
import static com.didekinlib.http.usuario.UsuarioServConstant.LOGIN;
import static com.didekinlib.http.usuario.UsuarioServConstant.OPEN;
import static com.didekinlib.http.usuario.UsuarioServConstant.PASSWORD_MODIFY;
import static com.didekinlib.http.usuario.UsuarioServConstant.PASSWORD_SEND;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_PATH;
import static com.didekinlib.http.usuario.UsuarioServConstant.USER_READ;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.REG_COMU_AND_USER_AND_USERCOMU;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.REG_USER_USERCOMU;
import static com.didekinlib.http.usuariocomunidad.UsuarioComunidadServConstant.USERCOMU_DELETE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 14/05/2018
 * Time: 15:35
 */
public class TkValidaPatternsTest {

    @Test
    public void test_tkEncrypted_direct()
    {
        String realTkStr = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0." +
                "." +
                "5VwgpFR0MD9ROmsvMiZ9RQ.aZ0npeqHqAqlLFXDutloQ845AZ8OlJg9x0zT89wMRGrv" +
                "8WLIEQwerIFA9V4JfXrpMV1MxBQpc2jc8gGKRA1tZesffzkZ5TuYQn9Vyn21VYS9KbeF" +
                "d9qYzKS7vQxhK_fEpjCgpPGt8-mZF745ImHVJt2zClXfEeyjfK9Lr2izcuqF9gChc_I" +
                "VJGAOkfo7sv7fYMHNL4PWdWmZdIInmMnqGQ." +
                "Y9C0goJx2Qij0JeLmRV_pA";
        assertThat(tkEncrypted_direct_symmetricKey_REGEX.isPatternOk(realTkStr), is(true));
    }

    @Test
    public void test_open_paths()
    {
        assertThat(closed_paths_REGEX.isPatternOk(OPEN.substring(1)), is(false));
        assertThat(closed_paths_REGEX.isPatternOk(LOGIN.substring(1)), is(false));
        assertThat(closed_paths_REGEX.isPatternOk(PASSWORD_SEND.substring(1)), is(false));
        assertThat(closed_paths_REGEX.isPatternOk(REG_COMU_AND_USER_AND_USERCOMU.substring(1)), is(false));
        assertThat(closed_paths_REGEX.isPatternOk(REG_USER_USERCOMU.substring(1)), is(false));
        assertThat(closed_paths_REGEX.isPatternOk(USER_PATH.substring(1)), is(true));
        assertThat(closed_paths_REGEX.isPatternOk(USER_READ.substring(1)), is(true));
        assertThat(closed_paths_REGEX.isPatternOk(PASSWORD_MODIFY.substring(1)), is(true));
        assertThat(closed_paths_REGEX.isPatternOk(USERCOMU_DELETE.substring(1)), is(true));
        assertThat(closed_paths_REGEX.isPatternOk(SEE_INCIDS_OPEN_BY_COMU.substring(1)), is(true));
        assertThat(closed_paths_REGEX.isPatternOk(MOD_RESOLUCION.substring(1)), is(true));
    }
}
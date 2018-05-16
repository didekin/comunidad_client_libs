package com.didekinlib.http.comunidad;

import com.didekinlib.http.usuario.UsuarioServConstant;

/**
 * User: pedro@didekin
 * Date: 12/11/15
 * Time: 17:06
 */

public final class ComunidadServConstant {


    public static final String COMUNIDAD_PATH = "/comunidad";
    public static final String COMUNIDAD_READ = COMUNIDAD_PATH + "/read";
    public static final String COMUNIDAD_SEARCH = UsuarioServConstant.OPEN + "/comunidad_search";

    private ComunidadServConstant()
    {
    }
}

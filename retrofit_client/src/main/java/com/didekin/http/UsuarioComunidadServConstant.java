package com.didekin.http;

import static com.didekin.http.ComunidadServConstant.COMUNIDAD_PATH;
import static com.didekin.http.ComunidadServConstant.COMUNIDAD_READ;
import static com.didekin.http.UsuarioServConstant.OPEN;
import static com.didekin.http.UsuarioServConstant.USER_READ;
import static com.didekin.http.UsuarioServConstant.USER_WRITE;

/**
 * User: pedro@didekin
 * Date: 30/05/16
 * Time: 16:59
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class UsuarioComunidadServConstant {

    public static final String COMUNIDAD_WRITE = COMUNIDAD_PATH + "/write";
    public static final String COMUNIDAD_OLDEST_USER = COMUNIDAD_READ + "/oldest_user";
    public static final String COMUS_BY_USER = USER_READ + "/comus_by_user";
    public static final String REG_COMU_AND_USER_AND_USERCOMU = OPEN + "/reg_comu_user_usercomu";
    public static final String REG_COMU_USERCOMU = USER_WRITE + "/reg_comu_usercomu";
    public static final String REG_USER_USERCOMU = OPEN + "/reg_user_usercomu";
    public static final String REG_USERCOMU = USER_WRITE + "/reg_usercomu";
    public static final String USERCOMU_DELETE = USER_WRITE + "/usercomus/delete";
    public static final String USERCOMU_MODIFY = USER_WRITE + "/usercomus";
    public static final String USERCOMU_READ = USER_READ + "/usercomus";
    public static final String USERCOMUS_BY_COMU = COMUNIDAD_READ + "/usercomus_by_comu";
    public static final String USERCOMUS_BY_USER = USER_READ + "/usercomus_by_user";

    private UsuarioComunidadServConstant()
    {
    }
}

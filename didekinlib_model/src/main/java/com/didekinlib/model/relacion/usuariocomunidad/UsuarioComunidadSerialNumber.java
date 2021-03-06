package com.didekinlib.model.relacion.usuariocomunidad;

import com.didekinlib.model.common.SerialNumber;

/**
 * User: pedro@didekin
 * Date: 09/11/15
 * Time: 16:25
 */
public enum UsuarioComunidadSerialNumber implements SerialNumber {

    USUARIO_COMUNIDAD(31001L),
    ;

    private final long number;

    UsuarioComunidadSerialNumber(long number)
    {
        this.number = number;
    }

    @Override
    public long serial()
    {
        return number;
    }
}

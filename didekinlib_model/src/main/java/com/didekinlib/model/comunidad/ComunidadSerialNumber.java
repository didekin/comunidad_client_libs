package com.didekinlib.model.comunidad;

import com.didekinlib.model.common.dominio.SerialNumber;

/**
 * User: pedro@didekin
 * Date: 09/11/15
 * Time: 16:25
 */
public enum ComunidadSerialNumber implements SerialNumber {


    COMUNIDAD(11000L),
    COMUNIDAD_AUTONOMA(12000L),
    MUNICIPIO(13000L),
    PROVINCIA(14000L),
    ;

    private final long number;

    ComunidadSerialNumber(long number)
    {
        this.number = number;
    }

    @Override
    public long serial()
    {
        return number;
    }
}

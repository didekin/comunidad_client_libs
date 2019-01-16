package com.didekinlib.model.entidad;

import com.didekinlib.model.common.SerialNumber;

/**
 * User: pedro@didekin
 * Date: 09/11/15
 * Time: 16:25
 */
public enum EntidadSerialNumber implements SerialNumber {


    COMUNIDAD(11001L),
    COMUNIDAD_AUTONOMA(12000L),
    ENTIDAD(17000L),
    MUNICIPIO(13000L),
    PROVEEDOR(16000L),
    PROVINCIA(14000L),
    DOMICILIO(15000L),
    ;

    private final long number;

    EntidadSerialNumber(long number)
    {
        this.number = number;
    }

    @Override
    public long serial()
    {
        return number;
    }
}

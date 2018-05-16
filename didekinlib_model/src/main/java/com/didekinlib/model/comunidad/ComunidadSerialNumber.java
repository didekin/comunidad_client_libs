package com.didekinlib.model.comunidad;

/**
 * User: pedro@didekin
 * Date: 09/11/15
 * Time: 16:25
 */
public enum ComunidadSerialNumber {


    COMUNIDAD(111L),
    COMUNIDAD_AUTONOMA(112L),
    MUNICIPIO(113L),
    PROVINCIA(114L),
    ;

    public final long number;

    ComunidadSerialNumber(long number)
    {
        this.number = number;
    }
}

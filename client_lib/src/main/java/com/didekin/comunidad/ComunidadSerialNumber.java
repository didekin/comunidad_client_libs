package com.didekin.comunidad;

/**
 * User: pedro@didekin
 * Date: 09/11/15
 * Time: 16:25
 */
public enum ComunidadSerialNumber {


    COMUNIDAD(21L),
    COMUNIDAD_AUTONOMA(3L),
    MUNICIPIO(5L),
    PROVINCIA(6L),
    ;

    public final long number;

    ComunidadSerialNumber(long number)
    {
        this.number = number;
    }
}

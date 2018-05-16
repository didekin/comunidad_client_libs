package com.didekinlib.model.usuario;

/**
 * User: pedro@didekin
 * Date: 09/11/15
 * Time: 16:25
 */
public enum UsuarioSerialNumber {

    USUARIO(221L),
    ;

    public final long number;

    UsuarioSerialNumber(long number)
    {
        this.number = number;
    }
}

package com.didekinlib.model.usuario;

import com.didekinlib.model.common.SerialNumber;

/**
 * User: pedro@didekin
 * Date: 09/11/15
 * Time: 16:25
 */
public enum UsuarioSerialNumber implements SerialNumber {

    USUARIO(21000L),
    ;

    private final long number;

    UsuarioSerialNumber(long number)
    {
        this.number = number;
    }

    @Override
    public long serial()
    {
        return number;
    }
}

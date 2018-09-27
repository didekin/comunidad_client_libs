package com.didekinlib.model.comunidad;

import java.io.Serializable;

import static com.didekinlib.model.comunidad.ComunidadSerialNumber.COMUNIDAD_AUTONOMA;

/**
 * User: pedro@didekin
 * Date: 16/06/15
 * Time: 15:09
 */

public class ComunidadAutonoma implements Serializable {

    private static final long serialVersionUID = COMUNIDAD_AUTONOMA.serial();

    private final short cuId;
    private final String nombre;

    public ComunidadAutonoma(short cuId)
    {
        this.cuId = cuId;
        nombre = null;
    }

    public ComunidadAutonoma(short cuId, String nombre)
    {
        this.cuId = cuId;
        this.nombre = nombre;
    }

    public short getCuId()
    {
        return cuId;
    }

    public String getNombre()
    {
        return nombre;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComunidadAutonoma that = (ComunidadAutonoma) o;

        return cuId == that.cuId;
    }

    @Override
    public int hashCode()
    {
        return (int) cuId;
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}

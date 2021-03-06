package com.didekinlib.model.entidad;


import java.io.Serializable;

import static java.lang.Short.compare;

/**
 * User: pedro@didekin
 * Date: 10/06/15
 * Time: 13:08
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class Provincia implements Comparable<Provincia>, Serializable{

    private static final long serialVersionUID = EntidadSerialNumber.PROVINCIA.serial();

    private final short provinciaId; // Es una PK fija, no un campo auto-increment.
    private final String nombre;
    private final ComunidadAutonoma comunidadAutonoma;

    public Provincia(short provinciaId)
    {
        this.provinciaId = provinciaId;
        nombre = null;
        comunidadAutonoma = null;
    }

    public Provincia(short provinciaId, String nombre)
    {
        this.nombre = nombre;
        this.provinciaId = provinciaId;
        comunidadAutonoma = null;
    }

    public Provincia(ComunidadAutonoma comunidadAutonoma, short provinciaId, String nombre)
    {
        this.comunidadAutonoma = comunidadAutonoma;
        this.provinciaId = provinciaId;
        this.nombre = nombre;
    }

    public short getProvinciaId()
    {
        return provinciaId;
    }

    public java.lang.String getNombre()
    {
        return nombre;
    }


    public ComunidadAutonoma getComunidadAutonoma()
    {
        return comunidadAutonoma;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Provincia provincia = (Provincia) o;

        return provinciaId == provincia.provinciaId;
    }

    @Override
    public int hashCode()
    {
        return (int) provinciaId;
    }

    @Override
    public int compareTo(Provincia o)
    {
        return compare(provinciaId, o.getProvinciaId());
    }

    @Override
    public String toString()
    {
        return nombre;
    }
}

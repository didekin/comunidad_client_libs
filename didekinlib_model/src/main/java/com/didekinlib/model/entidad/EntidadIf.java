package com.didekinlib.model.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Nonnull;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 10:34
 */
public interface EntidadIf<E extends IdFiscal, T extends EntidadIf> extends Serializable, Comparable<T> {

    long getEId();

    E getIdFiscal();

    String getNombre();

    Domicilio getDomicilio();

    Timestamp getFechaInicio();

    Timestamp getFechaBaja();

    @Override
    default int compareTo(@Nonnull T entidadIn)
    {
        return getDomicilio().compareTo(entidadIn.getDomicilio());
    }
}

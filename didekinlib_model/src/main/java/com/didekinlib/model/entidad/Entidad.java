package com.didekinlib.model.entidad;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 10:34
 */
public interface Entidad {

    long getId();

    IdFiscal getIdFiscal();

    String getNombre();

    Domicilio getDomicilio();
}

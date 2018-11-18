package com.didekinlib.model.incidencia.dominio;

import com.didekinlib.model.common.SerialNumber;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 14:35
 */
public enum  IncidenciaSerialNumber implements SerialNumber {

    AMBITO_INCIDENCIA(41000L),
    INCIDENCIA(42000L),
    INCID_IMPORTANCIA(43000L),
    INCID_COMMENT(44000L),
    INCID_RESOLUCION(45000L),
    INCID_RESOLUCION_AVANCE(46000L),
    INCID_RESOLUCION_BUNDLE(47000L),
    INCIDENCIA_USER(48000L),
    ;

    private final long number;

    IncidenciaSerialNumber(long number)
    {
        this.number = number;
    }

    @Override
    public long serial()
    {
        return number;
    }
}

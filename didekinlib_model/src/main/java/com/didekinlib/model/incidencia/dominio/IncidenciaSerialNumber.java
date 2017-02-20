package com.didekinlib.model.incidencia.dominio;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 14:35
 */
public enum  IncidenciaSerialNumber {

    AMBITO_INCIDENCIA(1L),
    INCIDENCIA(41L),
    INCID_IMPORTANCIA(44L),
    INCID_COMMENT(47),
    INCID_RESOLUCION(50L),
    INCID_RESOLUCION_AVANCE(53L),
    INCID_RESOLUCION_BUNDLE(56L),
    INCIDENCIA_USER(59L),
    ;

    public final long number;

    IncidenciaSerialNumber(long number)
    {
        this.number = number;
    }
}

package com.didekin.incidencia.dominio;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 14:35
 */
public enum  IncidenciaSerialNumber {

    AMBITO_INCIDENCIA(1L),
    INCIDENCIA(41L),
    INCID_IMPORTANCIA(42L),
    INCID_RESOLUCION(43L),
    INCID_RESOLUCION_AVANCE(44L),
    INCIDENCIA_USER(45L),
    ;

    public final long number;

    IncidenciaSerialNumber(long number)
    {
        this.number = number;
    }
}

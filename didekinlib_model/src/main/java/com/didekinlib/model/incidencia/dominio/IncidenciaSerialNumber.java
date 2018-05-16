package com.didekinlib.model.incidencia.dominio;

/**
 * User: pedro@didekin
 * Date: 20/11/16
 * Time: 14:35
 */
public enum  IncidenciaSerialNumber {

    AMBITO_INCIDENCIA(441L),
    INCIDENCIA(442L),
    INCID_IMPORTANCIA(443L),
    INCID_COMMENT(444),
    INCID_RESOLUCION(445L),
    INCID_RESOLUCION_AVANCE(446L),
    INCID_RESOLUCION_BUNDLE(447L),
    INCIDENCIA_USER(448L),
    ;

    public final long number;

    IncidenciaSerialNumber(long number)
    {
        this.number = number;
    }
}

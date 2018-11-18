package com.didekinlib.model.entidad;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 14:48
 */
public interface IdFiscal extends Comparable<IdFiscal> {

    String getIdFiscalStr();

    default int compareTo(IdFiscal idFiscalIn)
    {
        return getIdFiscalStr().compareTo(idFiscalIn.getIdFiscalStr());
    }
}

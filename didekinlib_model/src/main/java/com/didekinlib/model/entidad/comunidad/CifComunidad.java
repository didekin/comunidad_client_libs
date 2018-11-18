package com.didekinlib.model.entidad.comunidad;

import com.didekinlib.model.common.ValidDataPatterns;
import com.didekinlib.model.entidad.IdFiscal;

import java.io.Serializable;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 11:28
 */
public final class CifComunidad implements Serializable, IdFiscal {

    public static final String wrong_cif_comunidad = "Wrong CIF COMUNIDAD: ";
    public final String cifStr;

    private CifComunidad()
    {
        throw new UnsupportedOperationException(wrong_cif_comunidad + null);
    }

    public CifComunidad(String cifStrIn)
    {
        if (!ValidDataPatterns.CIF_COMUNIDAD.isPatternOk(cifStrIn)) {
            throw new IllegalArgumentException(wrong_cif_comunidad + cifStrIn);
        }
        cifStr = cifStrIn;
    }

    @Override
    public String getIdFiscalStr()
    {
        return cifStr;
    }

    @Override
    public boolean equals(Object cifIn)
    {
        if (!(cifIn instanceof CifComunidad)) {
            return false;
        }
        CifComunidad idFiscal = (CifComunidad) cifIn;
        return cifStr.equals(idFiscal.getIdFiscalStr());
    }
}

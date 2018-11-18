package com.didekinlib.model.entidad;

import java.io.Serializable;

import static com.didekinlib.model.common.ValidDataPatterns.CIF;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 11:28
 */
public final class Cif implements Serializable, IdFiscal {

    public static final String wrong_cif = "Wrong CIF: ";
    public final String cifStr;

    private Cif()
    {
        throw new UnsupportedOperationException(wrong_cif + null);
    }

    public Cif(String cifStrIn)
    {
        if (!CIF.isPatternOk(cifStrIn)) {
            throw new IllegalArgumentException(wrong_cif + cifStrIn);
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
        if (!(cifIn instanceof Cif)) {
            return false;
        }
        Cif idFiscal = (Cif) cifIn;
        return cifStr.equals(idFiscal.getIdFiscalStr());
    }
}

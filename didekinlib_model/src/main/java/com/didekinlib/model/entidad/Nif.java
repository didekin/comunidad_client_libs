package com.didekinlib.model.entidad;

import java.io.Serializable;

import static com.didekinlib.model.common.ValidDataPatterns.NIF;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 11:28
 */
public final class Nif implements Serializable, IdFiscal {

    public static final String wrong_nif = "Wrong NIF: ";
    public final String nifStr;

    private Nif()
    {
        throw new UnsupportedOperationException(wrong_nif + null);
    }

    public Nif(String nifStrIn)
    {
        if (!NIF.isPatternOk(nifStrIn)) {
            throw new IllegalArgumentException(wrong_nif + nifStrIn);
        }
        nifStr = nifStrIn;
    }

    @Override
    public String getIdFiscalStr()
    {
        return nifStr;
    }

    @Override
    public boolean equals(Object nifIn)
    {
        if (!(nifIn instanceof Nif)) {
            return false;
        }
        Nif idFiscal = (Nif) nifIn;
        return nifStr.equals(idFiscal.getIdFiscalStr());
    }
}

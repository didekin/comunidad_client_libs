package com.didekinlib.model.entidad;

import java.io.Serializable;

import static com.didekinlib.model.common.ValidDataPatterns.NIE;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 11:28
 */
public final class Nie implements Serializable, IdFiscal {

    public static final String wrong_nie = "Wrong NIE: ";
    public final String nieStr;

    private Nie()
    {
        throw new UnsupportedOperationException(wrong_nie + null);
    }

    public Nie(String nieStrIn)
    {
        if (!NIE.isPatternOk(nieStrIn)) {
            throw new IllegalArgumentException(wrong_nie + nieStrIn);
        }
        nieStr = nieStrIn;
    }

    @Override
    public String getIdFiscalStr()
    {
        return nieStr;
    }

    @Override
    public boolean equals(Object nieIn)
    {
        if (!(nieIn instanceof Nie)) {
            return false;
        }
        Nie idFiscal = (Nie) nieIn;
        return nieStr.equals(idFiscal.getIdFiscalStr());
    }
}

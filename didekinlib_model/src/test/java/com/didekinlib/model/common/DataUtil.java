package com.didekinlib.model.common;

import com.didekinlib.model.entidad.Cif;
import com.didekinlib.model.entidad.Domicilio;
import com.didekinlib.model.entidad.Municipio;
import com.didekinlib.model.entidad.Nie;
import com.didekinlib.model.entidad.Nif;
import com.didekinlib.model.entidad.Provincia;

/**
 * User: pedro@didekin
 * Date: 18/11/2018
 * Time: 13:16
 */
public class DataUtil {

    public static final Municipio municipio_1 = new Municipio((short) 23, new Provincia((short) 11));
    public static final Municipio municipio_2 = new Municipio((short) 2, new Provincia((short) 21));

    public static final Domicilio domicilio_0 = new Domicilio.DomicilioBuilder()
            .tipoVia("tipo1")
            .nombreVia("nombreA")
            .numero((short) 2)
            .municipio(municipio_1).build();

    public static final Domicilio domicilio_1 = new Domicilio.DomicilioBuilder()
            .copyDomicilioNonNullValues(domicilio_0)
            .sufijoNumero("A")
            .build();

    public static final Cif cif_ok = new Cif("Q7850003J");
    public static final String cif_wrong = "A1234567W";
    public static final Nif nif_ok = new Nif("0012345V");
    public static final String nif_wrong = "1234567HZ";
    public static final Nie nie_ok = new Nie("Z1234567R");
    public static final String nie_wrong = "X12345678";
}

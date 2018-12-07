package com.didekinlib.model.entidad;

import com.didekinlib.BeanBuilder;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static com.didekinlib.model.entidad.EntidadSerialNumber.DOMICILIO;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 12:36
 */
public class Domicilio implements Comparable<Domicilio>, Serializable {

    private final String tipoVia;     // not null.
    private final String nombreVia;   // not null.
    private final short numero;
    private final String sufijoNumero;
    private final Municipio municipio;  // not null.

    private Domicilio(DomicilioBuilder builder)
    {
        tipoVia = builder.tipoVia;
        nombreVia = builder.nombreVia;
        numero = builder.numero;
        sufijoNumero = builder.sufijoNumero;
        municipio = builder.municipio;
    }

    public String getDomicilioStr()
    {
        StringBuilder nombreBuilder = new StringBuilder();

        if (tipoVia != null && tipoVia.trim().length() > 0) {
            nombreBuilder.append(tipoVia).append(" ");
        }
        if (nombreVia != null && nombreVia.trim().length() > 0) {
            nombreBuilder.append(nombreVia).append(" ");
        }
        nombreBuilder.append(numero);
        if (sufijoNumero != null && !sufijoNumero.trim().isEmpty()) {
            nombreBuilder.append(" ").append(sufijoNumero.trim());
        }
        return nombreBuilder.toString();
    }

    public String getNombreVia()
    {
        return nombreVia;
    }

    public String getSufijoNumero()
    {
        return sufijoNumero;
    }

    public Municipio getMunicipio()
    {
        return municipio;
    }

    public short getNumero()
    {
        return numero;
    }

    public String getTipoVia()
    {
        return tipoVia;
    }

    @Override
    public String toString()
    {
        return getDomicilioStr();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Domicilio domicilio = (Domicilio) o;

        return tipoVia.equals(domicilio.tipoVia)
                && nombreVia.equals(domicilio.nombreVia)
                && numero == domicilio.numero
                && (sufijoNumero != null ? sufijoNumero.equals(domicilio.sufijoNumero) : domicilio.sufijoNumero == null)
                && municipio.equals(domicilio.municipio);
    }

    @Override
    public int hashCode()
    {
        int hash = tipoVia.hashCode();
        hash = 31 * hash + nombreVia.hashCode();
        hash = 31 * hash + numero;
        hash = sufijoNumero != null ? 31 * hash + sufijoNumero.hashCode() : 1;
        hash = 31 * hash + municipio.hashCode();
        return hash;
    }

    // .................................... Comparable ...........................

    @Override
    public int compareTo(Domicilio domicilioIn)
    {
        int result = municipio.compareTo(domicilioIn.getMunicipio());
        if (result != 0) {
            return result;
        }

        result = nombreVia.compareToIgnoreCase(domicilioIn.getNombreVia());
        if (result != 0) {
            return result;
        }

        result = tipoVia.compareToIgnoreCase(domicilioIn.getTipoVia());
        if (result != 0) {
            return result;
        }

        if (numero != domicilioIn.getNumero()) {
            return numero < domicilioIn.getNumero() ? -1 : 1;
        }
        if (sufijoNumero != null) {
            return sufijoNumero.compareToIgnoreCase(domicilioIn.getSufijoNumero());
        } else {
            return domicilioIn.getSufijoNumero() == null ? 0 : 1;
        }
    }

    // .................................... Serializable ...........................

    /**
     * Return an InnerSerial object that will replace the current Domicilio object during serialization.
     * In the deserialization the readResolve() method of the InnerSerial object will be used.
     */
    private Object writeReplace()
    {
        return new Domicilio.DomicilioSerial(this);
    }

    private void readObject(ObjectInputStream inputStream) throws InvalidObjectException
    {
        throw new InvalidObjectException("Use innerSerial to serialize");
    }

    //    ==================== BUILDER ====================

    public static class DomicilioBuilder implements BeanBuilder<Domicilio> {


        private String tipoVia = null;
        private String nombreVia = null;
        private short numero = (short) 0;
        private String sufijoNumero = "";
        private Municipio municipio = null;

        public DomicilioBuilder()
        {
        }

        public DomicilioBuilder copyDomicilioNonNullValues(Domicilio initValue)
        {
            if (initValue.tipoVia != null && !initValue.tipoVia.isEmpty()) {
                tipoVia = initValue.tipoVia;
            }
            if (initValue.nombreVia != null && !initValue.nombreVia.isEmpty()) {
                nombreVia = initValue.nombreVia;
            }
            if (initValue.numero > 0) {
                numero = initValue.numero;
            }
            if (initValue.sufijoNumero != null && !initValue.sufijoNumero.isEmpty()) {
                sufijoNumero = initValue.sufijoNumero;
            }
            if (initValue.municipio != null) {
                municipio = initValue.municipio;
            }
            return this;
        }

        public DomicilioBuilder tipoVia(String initValue)
        {
            tipoVia = initValue;
            return this;
        }

        public DomicilioBuilder nombreVia(String initValue)
        {
            nombreVia = initValue;
            return this;
        }

        public DomicilioBuilder numero(short initValue)
        {
            numero = initValue;
            return this;
        }

        public DomicilioBuilder sufijoNumero(String initValue)
        {
            sufijoNumero = initValue;
            return this;
        }

        public DomicilioBuilder municipio(Municipio initValue)
        {
            municipio = initValue;
            return this;
        }

        @Override
        public Domicilio build()
        {
            Domicilio domicilio = new Domicilio(this);

            if (domicilio.tipoVia == null || domicilio.nombreVia == null || domicilio.municipio == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return domicilio;
        }

        public Domicilio buildNoMunicipio()
        {
            Domicilio domicilio = new Domicilio(this);

            if (domicilio.tipoVia == null || domicilio.nombreVia == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return domicilio;
        }
    }

    //    ================================== SERIALIZATION PROXY ==================================

    private static class DomicilioSerial implements Serializable {

        private static final long serialVersionUID = DOMICILIO.serial();

        private final String tipoVia;
        private final String nombreVia;
        private final short numero;
        private final String sufijoNumero;
        private final Municipio municipio;

        public DomicilioSerial(Domicilio domicilioIn)
        {
            tipoVia = domicilioIn.tipoVia;
            nombreVia = domicilioIn.nombreVia;
            numero = domicilioIn.numero;
            sufijoNumero = domicilioIn.sufijoNumero;
            municipio = domicilioIn.municipio;
        }

        private Object readResolve()
        {
            return new DomicilioBuilder()
                    .tipoVia(tipoVia)
                    .nombreVia(nombreVia)
                    .numero(numero)
                    .sufijoNumero(sufijoNumero)
                    .municipio(municipio)
                    .build();
        }
    }
}

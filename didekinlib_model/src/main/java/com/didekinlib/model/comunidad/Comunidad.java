package com.didekinlib.model.comunidad;


import com.didekinlib.model.common.dominio.BeanBuilder;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.model.common.dominio.BeanBuilder.error_message_bean_building;


/**
 * User: pedro
 * Date: 29/03/15
 * Time: 12:02
 */
public final class Comunidad implements Comparable<Comunidad>, Serializable {

    private final long c_Id;
    private final String tipoVia;     // not null.
    private final String nombreVia;   // not null.
    private final short numero;
    private final String sufijoNumero;
    private final Municipio municipio;  // not null.
    private final Timestamp fechaAlta;
    private final Timestamp fechaMod;

    private Comunidad(ComunidadBuilder builder)
    {
        c_Id = builder.c_Id;
        tipoVia = builder.tipoVia;
        nombreVia = builder.nombreVia;
        numero = builder.numero;
        sufijoNumero = builder.sufijoNumero;
        municipio = builder.municipio;
        fechaAlta = builder.fechaAlta;
        fechaMod = builder.fechaMod;
    }

    public long getC_Id()
    {
        return c_Id;
    }


    public String getNombreComunidad()
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

    public Timestamp getFechaAlta()
    {
        return fechaAlta != null ? new Timestamp(fechaAlta.getTime()) : null;
    }

    public Timestamp getFechaMod()
    {
        return fechaMod != null ? new Timestamp(fechaMod.getTime()) : null;
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

    // .................................... Serializable ...........................

    /**
     * Return an InnerSerial object that will replace the current Comunidad object during serialization.
     * In the deserialization the readResolve() method of the InnerSerial object will be used.
     */
    private Object writeReplace()
    {
        return new ComunidadSerial(this);
    }

    private void readObject(ObjectInputStream inputStream) throws InvalidObjectException
    {
        throw new InvalidObjectException("Use innerSerial to serialize");
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

        Comunidad comunidad = (Comunidad) o;

        // Comunidad is initialized with a PK.
        if (c_Id > 0 && comunidad.getC_Id() > 0) {
            return c_Id == comunidad.getC_Id();
        }

        return tipoVia.equals(comunidad.tipoVia)
                && nombreVia.equals(comunidad.nombreVia)
                && numero == comunidad.numero
                && (sufijoNumero != null ? sufijoNumero.equals(comunidad.sufijoNumero) : comunidad.sufijoNumero == null)
                && municipio.equals(comunidad.municipio);
    }

    @Override
    public String toString()
    {
        return getNombreComunidad();
    }

    @Override
    public int hashCode()
    {
        if (c_Id > 0) {
            return ((int) (c_Id ^ (c_Id >>> 32))) * 31;
        }

        if (tipoVia == null || nombreVia == null || municipio == null) {
            throw new UnsupportedOperationException(error_message_bean_building + this.getClass().getName());
        }

        int hash = tipoVia.hashCode();
        hash = 31 * hash + nombreVia.hashCode();
        hash = 31 * hash + numero;
        hash = sufijoNumero != null ? 31 * hash + sufijoNumero.hashCode() : 1;
        hash = 31 * hash + municipio.hashCode();
        return hash;
    }

    @Override
    public int compareTo(Comunidad comunidadIn)
    {
        if (municipio == null || nombreVia == null || tipoVia == null) {
            throw new UnsupportedOperationException(error_message_bean_building + this.getClass().getName());
        }

        int result = municipio.compareTo(comunidadIn.getMunicipio());
        if (result != 0) {
            return result;
        }

        result = nombreVia.compareToIgnoreCase(comunidadIn.getNombreVia());
        if (result != 0) {
            return result;
        }

        result = tipoVia.compareToIgnoreCase(comunidadIn.getTipoVia());
        if (result != 0) {
            return result;
        }

        if (numero != comunidadIn.getNumero()) {
            return numero < comunidadIn.getNumero() ? -1 : 1;
        }
        if (sufijoNumero != null) {
            return sufijoNumero.compareToIgnoreCase(comunidadIn.getSufijoNumero());
        } else {
            return comunidadIn.getSufijoNumero() == null ? 0 : 1;
        }
    }

    //    ==================== BUILDER ====================

    public static class ComunidadBuilder implements BeanBuilder<Comunidad> {

        private long c_Id = 0L;
        private String tipoVia = null;
        private String nombreVia = null;
        private short numero = (short) 0;
        private String sufijoNumero = "";
        private Municipio municipio = null;
        private Timestamp fechaAlta = null;
        private Timestamp fechaMod = null;

        public ComunidadBuilder()
        {
        }

        public ComunidadBuilder copyComunidadNonNullValues(Comunidad initValue)
        {
            if (initValue.c_Id > 0L) {
                c_Id = initValue.c_Id;
            }
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
            if (initValue.fechaAlta != null) {
                fechaAlta = initValue.fechaAlta;
            }
            if (initValue.fechaMod != null) {
                fechaMod = initValue.fechaMod;
            }
            return this;
        }

        public ComunidadBuilder c_id(long initValue)
        {
            c_Id = initValue;
            return this;
        }

        public ComunidadBuilder tipoVia(String initValue)
        {
            tipoVia = initValue;
            return this;
        }

        public ComunidadBuilder nombreVia(String initValue)
        {
            nombreVia = initValue;
            return this;
        }

        public ComunidadBuilder numero(short initValue)
        {
            numero = initValue;
            return this;
        }

        public ComunidadBuilder sufijoNumero(String initValue)
        {
            sufijoNumero = initValue;
            return this;
        }

        public ComunidadBuilder municipio(Municipio initValue)
        {
            municipio = initValue;
            return this;
        }


        public ComunidadBuilder fechaAlta(Timestamp initValue)
        {
            fechaAlta = new Timestamp(initValue.getTime());
            return this;
        }


        public ComunidadBuilder fechaModificacion(Timestamp initValue)
        {
            fechaMod = new Timestamp(initValue.getTime());
            return this;
        }

        @Override
        public Comunidad build()
        {
            Comunidad comunidad = new Comunidad(this);

            if (comunidad.c_Id <= 0 && (comunidad.tipoVia == null || comunidad.nombreVia == null || comunidad
                    .municipio == null)) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return comunidad;
        }
    }

    //    ================================== SERIALIZATION PROXY ==================================

    private static class ComunidadSerial implements Serializable {

        private static final long serialVersionUID = ComunidadSerialNumber.COMUNIDAD.number;

        private final long c_Id;
        private final String tipoVia;
        private final String nombreVia;
        private final short numero;
        private final String sufijoNumero;
        private final Municipio municipio;

        public ComunidadSerial(Comunidad comunidad)
        {
            c_Id = comunidad.c_Id;
            tipoVia = comunidad.tipoVia;
            nombreVia = comunidad.nombreVia;
            numero = comunidad.numero;
            sufijoNumero = comunidad.sufijoNumero;
            municipio = comunidad.municipio;
        }

        private Object readResolve()
        {

            return new ComunidadBuilder()
                    .c_id(c_Id)
                    .tipoVia(tipoVia)
                    .nombreVia(nombreVia)
                    .numero(numero)
                    .sufijoNumero(sufijoNumero)
                    .municipio(municipio)
                    .build();
        }
    }
}

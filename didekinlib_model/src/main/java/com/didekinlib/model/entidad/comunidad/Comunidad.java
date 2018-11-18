package com.didekinlib.model.entidad.comunidad;


import com.didekinlib.BeanBuilder;
import com.didekinlib.model.entidad.Domicilio;
import com.didekinlib.model.entidad.Entidad;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static com.didekinlib.model.entidad.EntidadSerialNumber.COMUNIDAD;


/**
 * User: pedro
 * Date: 29/03/15
 * Time: 12:02
 */
public final class Comunidad implements Comparable<Comunidad>, Serializable, Entidad {

    private CifComunidad idFiscal;
    private final long c_Id;
    private final Domicilio domicilio;  // not null.

    private Comunidad(ComunidadBuilder builder)
    {
        c_Id = builder.c_Id;
        idFiscal = builder.idFiscal;
        domicilio = builder.domicilio;
    }

    @Override
    public long getId()
    {
        return c_Id;
    }

    @Override
    public CifComunidad getIdFiscal()
    {
        return idFiscal;
    }

    @Override
    public String getNombre()
    {
        return domicilio.getDomicilioStr();
    }

    @Override
    public Domicilio getDomicilio()
    {
        return domicilio;
    }

    //    public Timestamp getFechaAlta()
//    {
//        return fechaAlta != null ? new Timestamp(fechaAlta.getTime()) : null;
//    }

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
        if (c_Id > 0 && comunidad.getId() > 0) {
            return c_Id == comunidad.getId();
        }
        if (idFiscal != null && comunidad.idFiscal != null) {
            return idFiscal.equals(comunidad.getIdFiscal());
        }
        return domicilio.equals(comunidad.domicilio);
    }

    @Override
    public String toString()
    {
        return domicilio.getDomicilioStr();
    }

    @Override
    public int hashCode()
    {
        if (c_Id > 0) {
            return ((int) (c_Id ^ (c_Id >>> 32))) * 31;
        }
        return domicilio.hashCode();
    }

    // .................................... Comparable ...........................

    @Override
    public int compareTo(Comunidad comunidadIn)
    {
        return domicilio.compareTo(comunidadIn.domicilio);
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

    //    ==================== BUILDER ====================

    public static class ComunidadBuilder implements BeanBuilder<Comunidad> {

        private long c_Id = 0L;
        private CifComunidad idFiscal;
        private Domicilio domicilio;

        public ComunidadBuilder()
        {
        }

        public ComunidadBuilder copyComunidadNonNullValues(Comunidad initValue)
        {
            if (initValue.c_Id > 0L) {
                c_Id = initValue.c_Id;
            }
            if (initValue.idFiscal != null) {
                idFiscal = initValue.idFiscal;
            }
            if (initValue.domicilio != null) {
                domicilio = initValue.domicilio;
            }
            return this;
        }

        public ComunidadBuilder c_id(long initValue)
        {
            c_Id = initValue;
            return this;
        }

        public ComunidadBuilder idFiscal(CifComunidad idFiscalIn)
        {
            idFiscal = idFiscalIn;
            return this;
        }

        public ComunidadBuilder domicilio(Domicilio domicilioIn)
        {
            domicilio = domicilioIn;
            return this;
        }

        @Override
        public Comunidad build()
        {
            Comunidad comunidad = new Comunidad(this);

            if (comunidad.c_Id <= 0 && comunidad.domicilio == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return comunidad;
        }
    }

    //    ================================== SERIALIZATION PROXY ==================================

    private static class ComunidadSerial implements Serializable {

        private static final long serialVersionUID = COMUNIDAD.serial();

        private final long c_Id;
        private final CifComunidad idFiscal;
        private final Domicilio domicilio;

        public ComunidadSerial(Comunidad comunidad)
        {
            c_Id = comunidad.c_Id;
            idFiscal = comunidad.idFiscal;
            domicilio = comunidad.domicilio;
        }

        private Object readResolve()
        {
            return new ComunidadBuilder()
                    .c_id(c_Id)
                    .idFiscal(idFiscal)
                    .domicilio(domicilio)
                    .build();
        }
    }
}

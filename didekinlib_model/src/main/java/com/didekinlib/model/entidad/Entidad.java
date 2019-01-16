package com.didekinlib.model.entidad;

import com.didekinlib.BeanBuilder;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.BeanBuilder.error_message_bean_building;
import static com.didekinlib.model.entidad.EntidadSerialNumber.ENTIDAD;

/**
 * User: pedro@didekin
 * Date: 2019-01-10
 * Time: 13:38
 */
public final class Entidad<E extends IdFiscal> implements EntidadIf<E, Entidad> {

    private final long eId;
    private E idFiscal;
    private final Domicilio domicilio;
    private final Timestamp fechaInicio;
    private final Timestamp fechaBaja;

    private Entidad(Entidad.EntidadBuilder<E> builder)
    {
        eId = builder.eId;
        idFiscal = builder.idFiscal;
        domicilio = builder.domicilio;
        fechaInicio = builder.fechaInicio;
        fechaBaja = builder.fechaBaja;
    }

    @Override
    public long getEId()
    {
        return eId;
    }

    @Override
    public E getIdFiscal()
    {
        return idFiscal;
    }

    @Override
    public String getNombre()
    {
        throw new UnsupportedOperationException(error_message_bean_building + this.getClass().getName());
    }

    @Override
    public Domicilio getDomicilio()
    {
        return domicilio;
    }

    @Override
    public Timestamp getFechaInicio()
    {
        return fechaInicio;
    }

    @Override
    public Timestamp getFechaBaja()
    {
        return fechaBaja;
    }

    @Override
    public String toString()
    {
        return domicilio.getDomicilioStr();
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
        Entidad entidad = (Entidad) o;
        if (eId > 0 && entidad.getEId() > 0) {
            return eId == entidad.getEId();
        }
        if (idFiscal != null && entidad.idFiscal != null) {
            return idFiscal.equals(entidad.getIdFiscal());
        }
        return domicilio.equals(entidad.domicilio);
    }

    @Override
    public int hashCode()
    {
        if (eId > 0) {
            return ((int) (eId ^ (eId >>> 32))) * 31;
        }
        return domicilio.hashCode();
    }

    //    ==================== BUILDER ====================

    public static class EntidadBuilder<E extends IdFiscal> implements BeanBuilder<Entidad> {

        private long eId = 0L;
        private E idFiscal;
        private Domicilio domicilio;
        private Timestamp fechaInicio;
        private Timestamp fechaBaja;

        public EntidadBuilder()
        {
        }

        public Entidad.EntidadBuilder<E> copyEntidadNonNullValues(Entidad<E> initValue)
        {
            if (initValue.eId > 0L) {
                eId = initValue.eId;
            }
            if (initValue.idFiscal != null) {
                idFiscal = initValue.idFiscal;
            }
            if (initValue.domicilio != null) {
                domicilio = initValue.domicilio;
            }
            if (initValue.fechaInicio != null) {
                fechaInicio = initValue.fechaInicio;
            }
            if (initValue.fechaBaja != null) {
                fechaBaja = initValue.fechaBaja;
            }
            return this;
        }

        public Entidad.EntidadBuilder<E> eId(long initValue)
        {
            eId = initValue;
            return this;
        }

        public Entidad.EntidadBuilder<E> idFiscal(E idFiscalIn)
        {
            idFiscal = idFiscalIn;
            return this;
        }

        public Entidad.EntidadBuilder<E> domicilio(Domicilio domicilioIn)
        {
            domicilio = domicilioIn;
            return this;
        }

        public Entidad.EntidadBuilder<E> fechaInicio(Timestamp fechaInicioIn)
        {
            fechaInicio = fechaInicioIn;
            return this;
        }

        public Entidad.EntidadBuilder<E> fechaBaja(Timestamp fechaFinIn)
        {
            fechaBaja = fechaFinIn;
            return this;
        }

        @Override
        public Entidad<E> build()
        {
            Entidad<E> entidad = new Entidad<>(this);

            if (entidad.eId <= 0 && entidad.domicilio == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return entidad;
        }
    }

    //    ================================== SERIALIZATION PROXY ==================================

    public static class EntidadSerial<E extends IdFiscal> implements Serializable {

        private static final long serialVersionUID = ENTIDAD.serial();

        private final long eId;
        private E idFiscal;
        private final Domicilio domicilio;
        private final Timestamp fechaInicio;
        private final Timestamp fechaBaja;

        public EntidadSerial(Entidad<E> entidad)
        {
            eId = entidad.eId;
            idFiscal = entidad.idFiscal;
            domicilio = entidad.domicilio;
            fechaInicio = entidad.fechaInicio;
            fechaBaja = entidad.fechaBaja;
        }

        private Object readResolve()
        {
            return new Entidad.EntidadBuilder<E>()
                    .eId(eId)
                    .idFiscal(idFiscal)
                    .domicilio(domicilio)
                    .fechaInicio(fechaInicio)
                    .fechaBaja(fechaBaja)
                    .build();
        }
    }

    /**
     * Return an InnerSerial object that will replace the current Entidad object during serialization.
     * In the deserialization the readResolve() method of the InnerSerial object will be used.
     */
    private Object writeReplace()
    {
        return new Entidad.EntidadSerial<E>(this) {
        };
    }

    private void readObject(ObjectInputStream inputStream) throws InvalidObjectException
    {
        throw new InvalidObjectException("Use innerSerial to serialize");
    }
}

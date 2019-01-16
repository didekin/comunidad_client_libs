package com.didekinlib.model.entidad.proveedor;

import com.didekinlib.BeanBuilder;
import com.didekinlib.model.entidad.Domicilio;
import com.didekinlib.model.entidad.Entidad;
import com.didekinlib.model.entidad.EntidadIf;
import com.didekinlib.model.entidad.IdFiscal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.model.entidad.EntidadSerialNumber.PROVEEDOR;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 10:34
 */
public class Proveedor<E extends IdFiscal> implements EntidadIf<E, Proveedor> {

    private final Entidad<E> entidadIn;
    private final String nombreComercial;  // not null.

    private Proveedor(ProveedorBuilder<E> builder)
    {
        entidadIn = builder.entidadBuilder.build();
        nombreComercial = builder.nombreComercial;
    }

    // .................................... Serializable ...........................

    /**
     * Return an InnerSerial object that will replace the current Proveedor object during serialization.
     * In the deserialization the readResolve() method of the InnerSerial object will be used.
     */
    private Object writeReplace()
    {
        return new ProveedorSerial<>(this);
    }

    private void readObject(ObjectInputStream inputStream) throws InvalidObjectException
    {
        throw new InvalidObjectException("Use innerSerial to serialize");
    }

    @Override
    public long getEId()
    {
        return entidadIn.getEId();
    }

    @Override
    public E getIdFiscal()
    {
        return entidadIn.getIdFiscal();
    }

    @Override
    public String getNombre()
    {
        return nombreComercial;
    }

    @Override
    public Domicilio getDomicilio()
    {
        return entidadIn.getDomicilio();
    }

    @Override
    public Timestamp getFechaInicio()
    {
        return entidadIn.getFechaInicio();
    }

    @Override
    public Timestamp getFechaBaja()
    {
        return entidadIn.getFechaBaja();
    }

    //    ==================== BUILDER ====================

    public static class ProveedorBuilder<E extends IdFiscal> implements BeanBuilder<Proveedor> {

        private final Entidad.EntidadBuilder<E> entidadBuilder;
        private String nombreComercial; // not null.

        public ProveedorBuilder(Entidad.EntidadBuilder<E> entidadBuilder)
        {
            this.entidadBuilder = entidadBuilder;
        }

        public ProveedorBuilder<E> nombreComercial(String initValue)
        {
            nombreComercial = initValue;
            return this;
        }

        @Override
        public Proveedor<E> build()
        {
            Proveedor<E> proveedor = new Proveedor<>(this);
            if (proveedor.entidadIn.getEId() <= 0 && proveedor.nombreComercial == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return proveedor;
        }
    }

    //    ================================== SERIALIZATION PROXY ==================================

    private static class ProveedorSerial<E extends IdFiscal> implements Serializable {

        private static final long serialVersionUID = PROVEEDOR.serial();
        private final Entidad<E> entidad;
        private String nombreComercial;

        public ProveedorSerial(Proveedor<E> proveedor)
        {
            entidad = proveedor.entidadIn;
            nombreComercial = proveedor.nombreComercial;
        }

        private Object readResolve()
        {
            return new ProveedorBuilder<>(new Entidad.EntidadBuilder<E>().copyEntidadNonNullValues(entidad))
                    .nombreComercial(nombreComercial)
                    .build();
        }
    }
}

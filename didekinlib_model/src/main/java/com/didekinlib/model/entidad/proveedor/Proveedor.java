package com.didekinlib.model.entidad.proveedor;

import com.didekinlib.BeanBuilder;
import com.didekinlib.model.entidad.Domicilio;
import com.didekinlib.model.entidad.Entidad;
import com.didekinlib.model.entidad.IdFiscal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static com.didekinlib.model.entidad.EntidadSerialNumber.PROVEEDOR;

/**
 * User: pedro@didekin
 * Date: 17/11/2018
 * Time: 10:34
 */
public class Proveedor<E extends IdFiscal> implements Comparable<Proveedor>, Serializable, Entidad {

    private final long p_Id;
    private final Domicilio domicilio; // not null.
    private final E idFiscal;
    private final String nombreComercial;  // not null.

    private Proveedor(ProveedorBuilder<E> builder)
    {
        p_Id = builder.p_Id;
        idFiscal = builder.idFiscal;
        domicilio = builder.domicilio;
        nombreComercial = builder.nombreComercial;
    }

    @Override
    public long getId()
    {
        return p_Id;
    }

    @Override
    public E getIdFiscal()
    {
        return idFiscal;
    }

    @Override
    public String getNombre()
    {
        return nombreComercial;
    }

    @Override
    public Domicilio getDomicilio()
    {
        return domicilio;
    }

    @Override
    public boolean equals(Object proveedorIn)
    {
        if (this == proveedorIn) {
            return true;
        }

        if (!(proveedorIn instanceof Proveedor)) {
            return false;
        }

        Proveedor proveedor = (Proveedor) proveedorIn;
        if (p_Id > 0 && proveedor.getId() > 0) {
            return p_Id == proveedor.getId();
        }
        if (idFiscal != null && proveedor.idFiscal != null) {
            return idFiscal.equals(proveedor.getIdFiscal());
        }
        return domicilio.equals(proveedor.domicilio) && nombreComercial.equals(proveedor.nombreComercial);
    }

    @Override
    public String toString()
    {
        return nombreComercial;
    }

    @Override
    public int hashCode()
    {
        if (p_Id > 0) {
            return ((int) (p_Id ^ (p_Id >>> 32))) * 31;
        }
        if (idFiscal != null) {
            return idFiscal.hashCode();
        }
        return 31 * domicilio.hashCode() + nombreComercial.hashCode();
    }

    // .................................... Comparable ...........................

    @Override
    public int compareTo(Proveedor proveedor)
    {
        return nombreComercial.compareTo(proveedor.nombreComercial);
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

    //    ==================== BUILDER ====================

    public static class ProveedorBuilder<E extends IdFiscal> implements BeanBuilder<Proveedor<E>> {

        private long p_Id;
        private Domicilio domicilio; // not null.
        private E idFiscal;
        private String nombreComercial; // not null.

        public ProveedorBuilder()
        {
        }

        public ProveedorBuilder<E> copyProveedorNonNullValues(Proveedor<E> proveedorIn)
        {
            if (proveedorIn.p_Id > 0L) {
                p_Id = proveedorIn.p_Id;
            }
            if (proveedorIn.idFiscal != null) {
                idFiscal = proveedorIn.idFiscal;
            }
            domicilio = proveedorIn.domicilio;
            nombreComercial = proveedorIn.nombreComercial;
            return this;
        }

        public ProveedorBuilder<E> p_id(long initValue)
        {
            p_Id = initValue;
            return this;
        }

        public ProveedorBuilder<E> idFiscal(E initValue)
        {
            idFiscal = initValue;
            return this;
        }

        public ProveedorBuilder<E> domicilio(Domicilio initValue)
        {
            domicilio = initValue;
            return this;
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
            if (proveedor.p_Id <= 0 && (proveedor.domicilio == null || proveedor.nombreComercial == null)) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return proveedor;
        }
    }

    //    ================================== SERIALIZATION PROXY ==================================

    private static class ProveedorSerial<E extends IdFiscal> implements Serializable {

        private static final long serialVersionUID = PROVEEDOR.serial();

        private long p_Id;
        private Domicilio domicilio;
        private E idFiscal;
        private String nombreComercial;

        public ProveedorSerial(Proveedor<E> proveedor)
        {
            p_Id = proveedor.p_Id;
            domicilio = proveedor.domicilio;
            idFiscal = proveedor.idFiscal;
            nombreComercial = proveedor.nombreComercial;
        }

        private Object readResolve()
        {
            return new ProveedorBuilder<>()
                    .p_id(p_Id)
                    .domicilio(domicilio)
                    .idFiscal(idFiscal)
                    .nombreComercial(nombreComercial)
                    .build();
        }
    }
}

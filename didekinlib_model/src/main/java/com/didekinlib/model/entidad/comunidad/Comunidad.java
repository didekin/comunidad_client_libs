package com.didekinlib.model.entidad.comunidad;


import com.didekinlib.model.entidad.Domicilio;
import com.didekinlib.model.entidad.Entidad;
import com.didekinlib.model.entidad.EntidadIf;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.model.entidad.EntidadSerialNumber.COMUNIDAD;


/**
 * User: pedro
 * Date: 29/03/15
 * Time: 12:02
 */
public final class Comunidad implements EntidadIf<CifComunidad, Comunidad> {

    private final Entidad<CifComunidad> entidadIn;

    public Comunidad(Entidad.EntidadBuilder<CifComunidad> builder)
    {
        entidadIn = builder.build();
    }

    @Override
    public long getEId()
    {
        return entidadIn.getEId();
    }

    @Override
    public CifComunidad getIdFiscal()
    {
        return entidadIn.getIdFiscal();
    }

    @Override
    public String getNombre()
    {
        return entidadIn.getDomicilio().getDomicilioStr();
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

    //    ================================== SERIALIZATION PROXY ==================================

    private static class ComunidadSerial implements Serializable {

        private static final long serialVersionUID = COMUNIDAD.serial();
        private final Entidad<CifComunidad> entidad;

        public ComunidadSerial(Comunidad comunidad)
        {
            entidad = comunidad.entidadIn;
        }

        private Object readResolve()
        {
            return new Comunidad(new Entidad.EntidadBuilder<CifComunidad>().copyEntidadNonNullValues(entidad));
        }
    }

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
}

package com.didekinlib.model.incidencia.dominio;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static com.didekinlib.model.incidencia.dominio.IncidenciaSerialNumber.INCID_RESOLUCION_BUNDLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * User: pedro@didekin
 * Date: 17/03/16
 * Time: 15:34
 */
@SuppressWarnings("unused, WeakerAccess")
public final class IncidAndResolBundle implements Serializable {

    private final IncidImportancia incidImportancia;
    private final boolean hasResolucion;

    public IncidAndResolBundle(IncidImportancia incidImportancia, boolean hasResolucion)
    {
        this.incidImportancia = incidImportancia;
        this.hasResolucion = hasResolucion;
    }

    public IncidImportancia getIncidImportancia()
    {
        return incidImportancia;
    }

    public boolean hasResolucion()
    {
        return hasResolucion;
    }

    @Override
    public int hashCode()
    {
        int result = incidImportancia.hashCode();
        return (31 * result) + (hasResolucion ? TRUE.hashCode() : FALSE.hashCode());
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj){
            return true;
        }
        if (!(obj instanceof IncidAndResolBundle)){
            return false;
        }
        IncidAndResolBundle objectOut = (IncidAndResolBundle) obj;
        return incidImportancia.equals(objectOut.incidImportancia) && hasResolucion == objectOut.hasResolucion;
    }

    //    ============================== SERIALIZATION PROXY ==================================

    /**
     * Return an InnerSerial object that will replace the current Avance instance during serialization.
     * In the deserialization the readResolve() method of the InnerSerial object will be used.
     */
    private Object writeReplace()
    {
        return new InnerSerial(this);
    }

    private void readObject(ObjectInputStream inputStream) throws InvalidObjectException
    {
        throw new InvalidObjectException("Use innerSerial to serialize");
    }

    private static class InnerSerial implements Serializable {

        private static final long serialVersionUID = INCID_RESOLUCION_BUNDLE.number;

        private final IncidImportancia incidImportancia;
        private final boolean hasResolucion;


        public InnerSerial(IncidAndResolBundle bundle)
        {
            incidImportancia = bundle.incidImportancia;
            hasResolucion = bundle.hasResolucion;
        }

        /**
         * Returns a logically equivalent InnerSerial instance of the enclosing class instance,
         * that will replace it during deserialization.
         */
        private Object readResolve()
        {
            return new IncidAndResolBundle(
                    new IncidImportancia.IncidImportanciaBuilder(incidImportancia.getIncidencia())
                            .copyIncidImportancia(incidImportancia)
                            .build(),
                    hasResolucion);
        }
    }
}

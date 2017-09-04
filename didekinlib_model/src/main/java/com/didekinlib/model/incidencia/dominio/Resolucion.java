package com.didekinlib.model.incidencia.dominio;

import com.didekinlib.model.common.dominio.BeanBuilder;
import com.didekinlib.model.common.gcm.GcmToComunidadHelper;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.didekinlib.model.incidencia.dominio.IncidenciaExceptionMsg.RESOLUCION_WRONG_INIT;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

/**
 * User: pedro@didekin
 * Date: 12/11/15
 * Time: 18:30
 */

@SuppressWarnings({"PrivateMemberAccessBetweenOuterAndInnerClass", "unused", "WeakerAccess"})
public final class Resolucion implements Serializable, GcmToComunidadHelper {

    private final String userName;
    private final String descripcion;
    private final int costeEstimado;
    private final int costeFinal;
    private final String moraleja;
    //    private final Proveedor proveedor;
    private final Timestamp fechaPrev;
    private final Timestamp fechaAlta;
    private final Incidencia incidencia;
    private final List<Avance> avances;

    private Resolucion(ResolucionBuilder builder)
    {
        userName = builder.userName;
        descripcion = builder.descripcion;
        costeEstimado = builder.costeEstimado;
        costeFinal = builder.costeFinal;
        moraleja = builder.moraleja;
//        proveedor = builder.proveedor;
        fechaPrev = builder.fechaPrev;
        fechaAlta = builder.fechaAlta;
        incidencia = builder.incidencia;
        avances = builder.avances;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Resolucion)) return false;

        Resolucion that = (Resolucion) o;

        return incidencia.equals(that.incidencia);

    }

    @Override
    public int hashCode()
    {
        return incidencia.hashCode();
    }

    public String getUserName()
    {
        return userName;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public int getCosteEstimado()
    {
        return costeEstimado;
    }

    public int getCosteFinal()
    {
        return costeFinal;
    }

    public String getMoraleja()
    {
        return moraleja;
    }

    public Timestamp getFechaPrev()
    {
        // Defensive copy.
        return fechaPrev != null ? new Timestamp(fechaPrev.getTime()) : null;
    }

    public Timestamp getFechaAlta()
    {
        // Defensive copy.
        return fechaAlta != null ? new Timestamp(fechaAlta.getTime()) : null;
    }

    public Incidencia getIncidencia()
    {
        return incidencia;
    }

    public List<Avance> getAvances()
    {
        if (avances != null && avances.size() > 0) {
            return unmodifiableList(avances);
        }
        return avances;
    }

    @Override
    public long getComunidadId()
    {
        return incidencia.getComunidad().getC_Id();
    }

    //    ===============================  BUILDER  ============================

    @SuppressWarnings("WeakerAccess")
    public static final class ResolucionBuilder implements BeanBuilder<Resolucion> {

        private String userName;
        private String descripcion;
        private int costeEstimado;
        private int costeFinal;
        //        private Proveedor proveedor;
        private Timestamp fechaPrev;
        public String moraleja;
        public Incidencia incidencia;
        public Timestamp fechaAlta;
        private List<Avance> avances;

        public ResolucionBuilder(Incidencia incidencia)
        {
            this.incidencia = incidencia;
        }

        public ResolucionBuilder userName(String initValue)
        {
            userName = initValue;
            return this;
        }

        public ResolucionBuilder descripcion(String initValue)
        {
            descripcion = initValue;
            return this;
        }

        public ResolucionBuilder costeEstimado(int initValue)
        {
            costeEstimado = initValue;
            return this;
        }

        public ResolucionBuilder costeReal(int initValue)
        {
            costeFinal = initValue;
            return this;
        }

        public ResolucionBuilder moraleja(String initValue)
        {
            moraleja = initValue;
            return this;
        }

        /*public ResolucionBuilder proveedor(Proveedor initValue)
        {
            proveedor = initValue;
            return this;
        }*/

        public ResolucionBuilder fechaPrevista(Timestamp initValue)
        {
            fechaPrev = initValue;
            return this;
        }

        public ResolucionBuilder fechaAlta(Timestamp initValue)
        {
            fechaAlta = initValue;
            return this;
        }

        @SuppressWarnings("unchecked")
        public ResolucionBuilder avances(List<Avance> avances)
        {
            List<Avance> emptyList = emptyList();
            this.avances = avances == null ? emptyList : unmodifiableList(avances);
            return this;
        }

        public ResolucionBuilder copyResolucion(Resolucion initValue)
        {
            userName = initValue.userName;
            descripcion = initValue.descripcion;
            costeEstimado = initValue.costeEstimado;
            costeFinal = initValue.costeFinal;
            fechaPrev = initValue.fechaPrev;
            moraleja = initValue.moraleja;
            fechaAlta = initValue.fechaAlta;
            avances = initValue.avances;
            return this;
        }

        @Override
        public Resolucion build()
        {
            Resolucion resolucion = new Resolucion(this);
            if (resolucion.incidencia == null
                    || resolucion.incidencia.getIncidenciaId() <= 0
                    || resolucion.descripcion == null
                    || resolucion.fechaPrev == null
                    ) {
                throw new IllegalStateException(RESOLUCION_WRONG_INIT.toString());
            }
            return resolucion;
        }

        public Resolucion buildAsFk()
        {
            Resolucion resolucion = new Resolucion(this);
            if (resolucion.incidencia == null || resolucion.incidencia.getIncidenciaId() <= 0) {
                throw new IllegalStateException(RESOLUCION_WRONG_INIT.toString());
            }
            return resolucion;
        }
    }

    //    ============================== SERIALIZATION PROXY ==================================

    /**
     * Return an InnerSerial object that will replace the current IncidImportancia instance during serialization.
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

    @SuppressWarnings("WeakerAccess")
    private static class InnerSerial implements Serializable {

        private static final long serialVersionUID = IncidenciaSerialNumber.INCID_RESOLUCION.number;

        private final String userName;
        private final String descripcion;
        private final int costeEstimado;
        private final int costeFinal;
        private final String moraleja;
        //    private final Proveedor proveedor;
        private final Timestamp fechaPrev;
        private final Timestamp fechaAlta;
        private final Incidencia incidencia;
        private final List<Avance> avances;

        public InnerSerial(Resolucion resolucion)
        {
            userName = resolucion.userName;
            descripcion = resolucion.descripcion;
            costeEstimado = resolucion.costeEstimado;
            costeFinal = resolucion.costeFinal;
            moraleja = resolucion.moraleja;
            fechaPrev = resolucion.fechaPrev;
            fechaAlta = resolucion.fechaAlta;
            incidencia = resolucion.incidencia;
            avances = resolucion.avances;
        }

        /**
         * Returns a logically equivalent InnerSerial instance of the enclosing class instance,
         * that will replace it during deserialization.
         */
        private Object readResolve()
        {
            return new ResolucionBuilder(incidencia)
                    .userName(userName)
                    .descripcion(descripcion)
                    .costeEstimado(costeEstimado)
                    .costeReal(costeFinal)
                    .moraleja(moraleja)
                    .fechaPrevista(fechaPrev)
                    .fechaAlta(fechaAlta)
                    .avances(avances)
                    .build();
        }
    }

    /*    ============================== STATIC UTILITY METHODS ==================================*/

    /**
     * @return true if the resolucion to be modified has an avance.
     */
    public static boolean hasResolToBeModifiedAvance(Resolucion resolucion)
    {
        return resolucion.getAvances() != null && resolucion.getAvances().size() == 1 && !resolucion.getAvances().get(0).getAvanceDesc().isEmpty();
    }

    /**
     * @param resolucion to be modified as sent by a client application.
     * @param userName   doing the modification.
     * @return a resolucion with the new data to be modified, including zero o one new avance.
     */
    public static Resolucion doResolucionModifiedWithNewAvance(Resolucion resolucion, String userName)
    {
        // Check for valid states of resolucion.avances in the instance to be persisted.
        if (resolucion.getAvances() != null && resolucion.getAvances().size() > 1) {
            throw new IllegalArgumentException(RESOLUCION_WRONG_INIT.toString());
        }

        List<Avance> avances = null;
        // Adds avance.
        if (hasResolToBeModifiedAvance(resolucion)) {
            avances = new ArrayList<>(1);
            avances.add(new Avance.AvanceBuilder()
                    .copyAvance(resolucion.getAvances().get(0))
                    .userName(userName)
                    .build());
        }
        // Only take into account possible modified fields.
        return new Resolucion.ResolucionBuilder(resolucion.getIncidencia())
                .fechaPrevista(resolucion.getFechaPrev())
                .costeEstimado(resolucion.getCosteEstimado())
                .avances(avances)
                .buildAsFk();
    }
}

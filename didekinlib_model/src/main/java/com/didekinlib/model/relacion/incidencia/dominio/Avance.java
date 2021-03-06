package com.didekinlib.model.relacion.incidencia.dominio;

import com.didekinlib.BeanBuilder;
import com.didekinlib.model.usuario.Usuario;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.model.relacion.incidencia.dominio.IncidenciaSerialNumber.INCID_RESOLUCION_AVANCE;

/**
 * User: pedro@didekin
 * Date: 11/03/16
 * Time: 12:03
 */
@SuppressWarnings({"PrivateMemberAccessBetweenOuterAndInnerClass", "WeakerAccess", "unused"})
public final class Avance implements Serializable {

    private final long avanceId;
    private final String avanceDesc;
    private final Usuario author;
    private final Timestamp fechaAlta;

    private Avance(AvanceBuilder builder)
    {
        avanceId = builder.avanceId;
        avanceDesc = builder.avanceDesc;
        author = builder.author;
        fechaAlta = builder.fechaAlta;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avance avance = (Avance) o;

        if (avanceId > 0L && (avanceId == avance.avanceId)) {
            return true;
        }

        if (author != null ? !author.equals(avance.author) : avance.author != null) {
            return false;
        }
        return !(fechaAlta != null ? !fechaAlta.equals(avance.fechaAlta) : avance.fechaAlta != null);
    }

    @Override
    public int hashCode()
    {
        if (avanceId > 0L) {
            return ((int) (avanceId ^ (avanceId >>> 32))) * 31;
        }

        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (fechaAlta != null ? fechaAlta.hashCode() : 0);
        return result;
    }

    public long getAvanceId()
    {
        return avanceId;
    }

    public String getAvanceDesc()
    {
        return avanceDesc;
    }

    public Usuario getAuthor()
    {
        return author;
    }

    public String getUserName()
    {
        return author.getUserName();
    }

    public String getAlias()
    {
        return author.getAlias();
    }

    public Timestamp getFechaAlta()
    {
        return fechaAlta != null ? new Timestamp(fechaAlta.getTime()) : null;
    }

    //    ===============================  BUILDER  ============================

    public static final class AvanceBuilder implements BeanBuilder<Avance> {

        private long avanceId;
        private String avanceDesc;
        private Usuario author;
        private Timestamp fechaAlta;

        public AvanceBuilder()
        {
        }

        public AvanceBuilder avanceId(long initValue)
        {
            avanceId = initValue;
            return this;
        }

        public AvanceBuilder avanceDesc(String initValue)
        {
            avanceDesc = initValue;
            return this;
        }

        public AvanceBuilder author(Usuario initValue)
        {
            author = initValue;
            return this;
        }

        public AvanceBuilder fechaAlta(Timestamp initValue)
        {
            fechaAlta = initValue;
            return this;
        }

        public AvanceBuilder copyAvance(Avance initValue)
        {
            avanceId = initValue.avanceId;
            avanceDesc = initValue.avanceDesc;
            author = initValue.author;
            fechaAlta = initValue.fechaAlta;
            return this;
        }

        @Override
        public Avance build()
        {
            Avance avance = new Avance(this);
            if (avanceId <= 0L && avance.avanceDesc == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return avance;
        }
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

        private static final long serialVersionUID = INCID_RESOLUCION_AVANCE.serial();

        private final long avanceId;
        private final String avanceDesc;
        private final Usuario author;
        private final Timestamp fechaAlta;


        public InnerSerial(Avance avance)
        {
            avanceId = avance.avanceId;
            avanceDesc = avance.avanceDesc;
            author = avance.author;
            fechaAlta = avance.fechaAlta;
        }

        /**
         * Returns a logically equivalent InnerSerial instance of the enclosing class instance,
         * that will replace it during deserialization.
         */
        private Object readResolve()
        {
            return new AvanceBuilder()
                    .avanceId(avanceId)
                    .avanceDesc(avanceDesc)
                    .author(author)
                    .fechaAlta(fechaAlta)
                    .build();
        }
    }
}

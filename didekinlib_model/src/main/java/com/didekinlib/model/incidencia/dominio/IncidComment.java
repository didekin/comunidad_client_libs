package com.didekinlib.model.incidencia.dominio;

import com.didekinlib.BeanBuilder;
import com.didekinlib.model.usuario.Usuario;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.model.incidencia.dominio.IncidenciaSerialNumber.INCID_COMMENT;

/**
 * User: pedro@didekin
 * Date: 03/02/16
 * Time: 10:40
 */
public final class IncidComment implements Serializable {

    private final long commentId;
    private final String descripcion;
    private final Incidencia incidencia;
    private final Usuario redactor;
    private final Timestamp fechaAlta;

    private IncidComment(IncidCommentBuilder builder)
    {
        commentId = builder.commentId;
        descripcion = builder.descripcion;
        incidencia = builder.incidencia;
        redactor = builder.redactor;
        fechaAlta = builder.fechaAlta;
    }

    public long getCommentId()
    {
        return commentId;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public Incidencia getIncidencia()
    {
        return incidencia;
    }

    public Usuario getRedactor()
    {
        return redactor;
    }

    public Timestamp getFechaAlta()
    {
        return fechaAlta != null ? new Timestamp(fechaAlta.getTime()) : null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof IncidComment)) return false;

        IncidComment comment = (IncidComment) o;

        return this.commentId == comment.commentId ||
                (descripcion.equals(comment.descripcion)
                        && incidencia.equals(comment.incidencia)
                );
    }

    @Override
    public int hashCode()
    {
        int result;

        if (commentId > 0) {
            result = (int) (commentId ^ (commentId >>> 32));
        } else {
            result = descripcion.hashCode();
            result = 31 * result + incidencia.hashCode();
        }
        return result;
    }

    // ==================== BUILDER ====================

    public final static class IncidCommentBuilder implements BeanBuilder<IncidComment> {

        private long commentId;
        private String descripcion;
        private Incidencia incidencia;
        private Usuario redactor;
        private Timestamp fechaAlta;

        public IncidCommentBuilder()
        {
        }

        public IncidCommentBuilder commentId(long initValue)
        {
            commentId = initValue;
            return this;
        }

        public IncidCommentBuilder descripcion(String initValue)
        {
            descripcion = initValue;
            return this;
        }

        public IncidCommentBuilder incidencia(Incidencia initValue)
        {
            incidencia = initValue;
            return this;
        }

        public IncidCommentBuilder redactor(Usuario initValue)
        {
            redactor = initValue;
            return this;
        }

        public IncidCommentBuilder fechaAlta(Timestamp initValue)
        {
            fechaAlta = initValue;
            return this;
        }

        public IncidCommentBuilder copyComment(IncidComment initValue)
        {
            commentId = initValue.commentId;
            descripcion = initValue.descripcion;
            incidencia = initValue.incidencia;
            redactor = initValue.redactor;
            fechaAlta = initValue.fechaAlta;
            return this;
        }

        @Override
        public IncidComment build()
        {
            IncidComment comment = new IncidComment(this);
            if (comment.commentId <= 0) {
                if (comment.descripcion == null || comment.incidencia == null) {
                    throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
                }
            }
            return comment;
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

        private static final long serialVersionUID = INCID_COMMENT.serial();

        private final long commentId;
        private final String descripcion;
        private final Incidencia incidencia;
        private final Usuario redactor;
        private final Timestamp fechaAlta;

        public InnerSerial(IncidComment comment)
        {
            commentId = comment.commentId;
            descripcion = comment.descripcion;
            incidencia = comment.incidencia;
            redactor = comment.redactor;
            fechaAlta = comment.fechaAlta;
        }

        /**
         * Returns a logically equivalent InnerSerial instance of the enclosing class instance,
         * that will replace it during deserialization.
         */
        private Object readResolve()
        {
            return new IncidCommentBuilder()
                    .commentId(commentId)
                    .descripcion(descripcion)
                    .incidencia(incidencia)
                    .redactor(redactor)
                    .fechaAlta(fechaAlta)
                    .build();
        }
    }
}

package com.didekinlib.model.relacion.incidencia.dominio;

import com.didekinlib.BeanBuilder;
import com.didekinlib.model.usuario.Usuario;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * User: pedro@didekin
 * Date: 12/11/15
 * Time: 17:08
 *
 * Holder object for the incidencia and its initiating user data, plus timeStamp for resolucion,
 * if there is one associated  to the incidencia.
 * Integrity constraint: incidencia.userName == usuario.userName, if both exists.
 */
@SuppressWarnings({"PrivateMemberAccessBetweenOuterAndInnerClass", "unused"})
public final class IncidenciaUser implements Serializable {

    private final Incidencia incidencia;
    private final Usuario usuario;
    private final Timestamp fechaAltaResolucion;

    private IncidenciaUser(IncidenciaUserBuilder builder) throws IllegalStateException
    {
        incidencia = builder.incidencia;
        usuario = builder.usuario;
        fechaAltaResolucion = builder.fechaAltaResolucion;
    }

    public Incidencia getIncidencia()
    {
        return incidencia;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public Timestamp getFechaAltaResolucion()
    {
        return fechaAltaResolucion != null ? new Timestamp(fechaAltaResolucion.getTime()) :  null;
    }

    // ............................ Serializable ...............................

    /**
     * Return an InnerSerial object that will replace the current IncicenciaUser object during serialization.
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof IncidenciaUser)) return false;

        IncidenciaUser that = (IncidenciaUser) o;

        return incidencia.equals(that.incidencia) && usuario.equals(that.usuario);
    }

    @Override
    public int hashCode()
    {
        int result = incidencia.hashCode();
        result = 31 * result + usuario.hashCode();
        return result;
    }

    //    ==================== BUILDER ====================


    public final static class IncidenciaUserBuilder implements BeanBuilder<IncidenciaUser> {

        private Incidencia incidencia;
        private Usuario usuario;
        private Timestamp fechaAltaResolucion;

        public IncidenciaUserBuilder(Incidencia incidencia)
        {
            this.incidencia = incidencia;
        }

        public IncidenciaUserBuilder usuario(Usuario initValue)
        {
            usuario = initValue;
            return this;
        }

        public IncidenciaUserBuilder fechaAltaResolucion(Timestamp initValue)
        {
            fechaAltaResolucion = initValue;
            return this;
        }

        public IncidenciaUserBuilder copyIncidUser(IncidenciaUser initValue)
        {
            usuario = initValue.usuario;
            return this;
        }

        @Override
        public IncidenciaUser build()
        {
            IncidenciaUser incidenciaUser = new IncidenciaUser(this);

            if (incidenciaUser.incidencia == null || incidenciaUser.incidencia.getUserName() == null){
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            if (!incidenciaUser.incidencia.getUserName().equals(incidenciaUser.usuario.getUserName())) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return incidenciaUser;
        }
    }

    //    ================================== SERIALIZATION PROXY ==================================


    private static class InnerSerial implements Serializable {

        private static final long serialVersionUID = IncidenciaSerialNumber.INCIDENCIA_USER.serial();

        private final Incidencia incidencia;
        private final Usuario usuario;
        private final Timestamp fechaAltaResolucion;

        public InnerSerial(IncidenciaUser incidenciaUser)
        {
            fechaAltaResolucion = incidenciaUser.fechaAltaResolucion;
            incidencia = incidenciaUser.incidencia;
            usuario = incidenciaUser.usuario;
        }

        private Object readResolve()
        {
            return (new IncidenciaUserBuilder(incidencia)
                    .usuario(usuario)
                    .fechaAltaResolucion(fechaAltaResolucion)
                    .build());
        }
    }
}

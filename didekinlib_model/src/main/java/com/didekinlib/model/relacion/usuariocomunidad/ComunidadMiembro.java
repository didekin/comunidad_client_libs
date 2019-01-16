package com.didekinlib.model.relacion.usuariocomunidad;


import com.didekinlib.BeanBuilder;
import com.didekinlib.model.entidad.comunidad.Comunidad;
import com.didekinlib.model.usuario.Usuario;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import static com.didekinlib.model.relacion.usuariocomunidad.UsuarioComunidadSerialNumber.MIEMBRO_COMUNIDAD;

/**
 * User: pedro
 * Date: 29/03/15
 * Time: 12:02
 */
public final class ComunidadMiembro implements Comparable<ComunidadMiembro>, Serializable {

    private final String portal;
    private final String escalera;
    private final String planta;
    private final String puerta;
    private final Timestamp fechaInicio;
    private final Timestamp fechaFin;

    private final Usuario usuario;
    private final Comunidad comunidad;

    private ComunidadMiembro(ComuMiembroBuilder comuMiembroBuilder)
    {
        portal = comuMiembroBuilder.portal;
        escalera = comuMiembroBuilder.escalera;
        planta = comuMiembroBuilder.planta;
        puerta = comuMiembroBuilder.puerta;
        fechaInicio = comuMiembroBuilder.fechaInicio;
        fechaFin = comuMiembroBuilder.fechaFin;
        usuario = comuMiembroBuilder.usuario;
        comunidad = comuMiembroBuilder.comunidad;
    }

    public Comunidad getEntidad()
    {
        return comunidad;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }


    public String getEscalera()
    {
        return escalera;
    }


    public String getPlanta()
    {
        return planta;
    }


    public String getPortal()
    {
        return portal;
    }


    public String getPuerta()
    {
        return puerta;
    }

    public Timestamp getFechaInicio()
    {
        return fechaInicio;
    }

    public Timestamp getFechaFin()
    {
        return fechaFin;
    }

    // ............................ Serializable ...............................

    /**
     * Return an InnerSerial object that will replace the current ComunidadMiembro object during serialization.
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
        if (!(o instanceof ComunidadMiembro)) return false;

        ComunidadMiembro that = (ComunidadMiembro) o;

        return usuario.equals(that.usuario) && comunidad.equals(that.comunidad);
    }

    @Override
    public int hashCode()
    {
        int result = usuario.hashCode();
        result = 31 * result + comunidad.hashCode();
        return result;
    }

    @Override
    public int compareTo(ComunidadMiembro o)
    {
        int result;

        if ((result = comunidad.compareTo(o.getEntidad())) != 0) {
            return result;
        }

        if ((result = usuario.compareTo(o.getUsuario())) != 0) {
            return result;
        }

        if (portal != null && o.getPortal() != null && (result = portal.compareToIgnoreCase(o.getPortal())) != 0) {
            return result;
        }
        if (escalera != null && o.getEscalera() != null && (result = escalera.compareToIgnoreCase(o.getEscalera())) != 0) {
            return result;
        }
        if (planta != null && o.getPlanta() != null && (result = planta.compareToIgnoreCase(o.getPlanta())) != 0) {
            return result;
        }
        if (puerta != null && o.getPuerta() != null && (result = puerta.compareToIgnoreCase(o.getPuerta())) != 0) {
            return result;
        }
        return 0;
    }

    //    ==================== BUILDER ====================

    public static class ComuMiembroBuilder implements BeanBuilder<ComunidadMiembro> {

        private String portal;
        private String escalera;
        private String planta;
        private String puerta;

        private Timestamp fechaInicio;
        private Timestamp fechaFin;

        // Required parameters.
        private Usuario usuario;
        private Comunidad comunidad;

        public ComuMiembroBuilder(Comunidad comunidad, Usuario usuario)
        {
            this.comunidad = comunidad;
            this.usuario = usuario;
        }

        @SuppressWarnings("Duplicates")
        public ComuMiembroBuilder comuMiembroRest(ComunidadMiembro initValue)
        {
            portal = initValue.portal;
            escalera = initValue.escalera;
            planta = initValue.planta;
            puerta = initValue.puerta;
            fechaInicio = initValue.fechaInicio;
            fechaFin = initValue.fechaFin;
            return this;
        }

        public ComuMiembroBuilder portal(String initValue)
        {
            portal = initValue;
            return this;
        }

        public ComuMiembroBuilder escalera(String initValue)
        {
            escalera = initValue;
            return this;
        }

        public ComuMiembroBuilder planta(String initValue)
        {
            planta = initValue;
            return this;
        }

        public ComuMiembroBuilder puerta(String initValue)
        {
            puerta = initValue;
            return this;
        }

        public ComuMiembroBuilder fechaInicio(Timestamp fechaInicioIn){
            fechaInicio = fechaInicioIn;
            return this;
        }

        public ComuMiembroBuilder fechaFin(Timestamp fechaFinIn){
            fechaFin = fechaFinIn;
            return this;
        }

        @Override
        public ComunidadMiembro build()
        {
            ComunidadMiembro userComu = new ComunidadMiembro(this);
            if (userComu.getEntidad() == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return userComu;
        }
    }

    //    ============================= SERIALIZATION PROXY ==================================

    private static class InnerSerial implements Serializable {

        private static final long serialVersionUID = MIEMBRO_COMUNIDAD.serial();

        private final Comunidad comunidad;
        private final Usuario usuario;
        private final String portal;
        private final String escalera;
        private final String planta;
        private final String puerta;
        private final Timestamp fechaInicio;
        private final Timestamp fechaFin;

        @SuppressWarnings("Duplicates")
        public InnerSerial(ComunidadMiembro comunidadMiembro)
        {
            comunidad = comunidadMiembro.comunidad;
            usuario = comunidadMiembro.usuario;
            portal = comunidadMiembro.portal;
            escalera = comunidadMiembro.escalera;
            planta = comunidadMiembro.planta;
            puerta = comunidadMiembro.puerta;
            fechaInicio = comunidadMiembro.fechaInicio;
            fechaFin = comunidadMiembro.fechaFin;
        }

        private Object readResolve()
        {
            return new ComuMiembroBuilder(comunidad, usuario)
                    .portal(portal)
                    .escalera(escalera)
                    .planta(planta)
                    .puerta(puerta)
                    .fechaInicio(fechaInicio)
                    .fechaFin(fechaFin)
                    .build();
        }
    }
}

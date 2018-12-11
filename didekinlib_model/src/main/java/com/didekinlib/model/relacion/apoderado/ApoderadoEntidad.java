package com.didekinlib.model.relacion.apoderado;

import com.didekinlib.model.entidad.Entidad;
import com.didekinlib.model.usuario.Usuario;

import java.sql.Timestamp;

/**
 * User: pedro@didekin
 * Date: 05/12/2018
 * Time: 13:37
 */
public interface ApoderadoEntidad  {
    Usuario getUsuario();
    Entidad getEntidad();
    Timestamp getFechaInicio();
    Timestamp getFechaFin();
}

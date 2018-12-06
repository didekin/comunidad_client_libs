package com.didekinlib.model.relacion.apoderado;

import com.didekinlib.model.entidad.Entidad;
import com.didekinlib.model.usuario.Usuario;

/**
 * User: pedro@didekin
 * Date: 05/12/2018
 * Time: 13:37
 */
public interface ApoderadoEntidad  {
    Usuario getUsuario();
    Entidad getEntidad();
}

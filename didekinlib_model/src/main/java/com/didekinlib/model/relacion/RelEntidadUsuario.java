package com.didekinlib.model.relacion;

import com.didekinlib.model.entidad.Entidad;
import com.didekinlib.model.usuario.Usuario;

/**
 * User: pedro@didekin
 * Date: 06/12/2018
 * Time: 12:43
 */
public interface RelEntidadUsuario<E extends Entidad> {
    E getEntidad();
    Usuario getUsuario();
}

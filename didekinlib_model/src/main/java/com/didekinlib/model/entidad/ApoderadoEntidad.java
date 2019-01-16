package com.didekinlib.model.entidad;

import com.didekinlib.model.entidad.EntidadIf;
import com.didekinlib.model.usuario.Usuario;

import java.sql.Timestamp;

/**
 * User: pedro@didekin
 * Date: 05/12/2018
 * Time: 13:37
 *
 * It can be useful to control the register of new members in an entidad. It should be done in a Tx signed by the apoderado.
 */
public interface ApoderadoEntidad  {
    Usuario getUsuario();
    EntidadIf getEntidad();
    Timestamp getFechaInicio();
    Timestamp getFechaFin();
}

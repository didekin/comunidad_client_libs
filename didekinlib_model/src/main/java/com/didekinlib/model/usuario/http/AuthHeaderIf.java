package com.didekinlib.model.usuario.http;

/**
 * User: pedro@didekin
 * Date: 10/06/2018
 * Time: 15:11
 */
public interface AuthHeaderIf {

    String toJsonString();

    String toBase64FromJsonStr();

    String getToken();
}

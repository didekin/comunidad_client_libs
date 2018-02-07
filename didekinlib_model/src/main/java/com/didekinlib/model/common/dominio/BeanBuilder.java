package com.didekinlib.model.common.dominio;

/**
 * User: pedro@didekin
 * Date: 31/08/15
 * Time: 13:09
 */
public interface BeanBuilder<T> {

    String error_message_bean_building = "Wrong initialization values in Builder: ";

    T build();
}

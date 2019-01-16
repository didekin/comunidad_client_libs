package com.didekinlib;

/**
 * User: pedro@didekin
 * Date: 31/08/15
 * Time: 13:09
 */
public interface BeanBuilder<T> {

    String error_message_bean_building = "Wrong initialization values in Builder: ";
    String error_message_hashcode = "Not possible to create hashCode";

    T build();
}

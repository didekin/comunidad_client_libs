package com.didekinlib.http.retrofit;

import com.didekinlib.http.exception.ErrorBean;

import java.io.IOException;

import retrofit2.Response;

/**
 * User: pedro@didekin
 * Date: 09/05/2018
 * Time: 10:57
 */
public interface HttpHandlerIf {

    <T> T getService(Class<T> endPointInterface);

    ErrorBean getErrorBean(Response<?> response) throws IOException;
}

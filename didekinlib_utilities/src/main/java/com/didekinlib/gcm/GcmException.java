package com.didekinlib.gcm;


import com.didekinlib.http.exception.ErrorBean;

/**
 * User: pedro@didekin
 * Date: 31/05/16
 * Time: 15:18
 */

public class GcmException extends RuntimeException {

    private final ErrorBean errorBean;

    public GcmException(ErrorBean errorBean)
    {
        this.errorBean = errorBean;
    }

    public ErrorBean getErrorBean()
    {
        return errorBean;
    }

    @Override
    public String getMessage()
    {
        return errorBean.getMessage();
    }
}

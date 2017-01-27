package com.didekinlib.gcm.model.common;


/**
 * User: pedro@didekin
 * Date: 31/05/16
 * Time: 15:18
 */
@SuppressWarnings("unused")
public class GcmException extends Exception {

    private final ErrorBean errorBean;

    public GcmException(ErrorBean errorBean)
    {
        this.errorBean = errorBean;
    }

    public ErrorBean getErrorBean()
    {
        return errorBean;
    }
}

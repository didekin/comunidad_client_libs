package com.didekinlib.gcm.model.common;

/**
 * User: pedro@didekin
 * Date: 02/06/16
 * Time: 10:07
 */
@SuppressWarnings("WeakerAccess")
public abstract class GcmRequestData {

    private final String typeMsg;

    protected GcmRequestData(String typeMsg)
    {
        this.typeMsg = typeMsg;
    }

    public String getTypeMsg()
    {
        return typeMsg;
    }
}

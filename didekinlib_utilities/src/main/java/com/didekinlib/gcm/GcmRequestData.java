package com.didekinlib.gcm;

/**
 * This class is used for 'data' JSON payload in FCM incidencia messages.
 */
public class GcmRequestData {

    private final long comunidadId;
    private final String typeMsg;

    public GcmRequestData(String typeMsg, long comunidadId)
    {
        this.comunidadId = comunidadId;
        this.typeMsg = typeMsg;
    }

    public String getTypeMsg()
    {
        return typeMsg;
    }

    public long getComunidadId()
    {
        return comunidadId;
    }
}

package com.didekinservice.incidservice.gcm;

import com.didekinservice.common.gcm.GcmRequestData;

/**
 * This class is used for 'data' JSON payload in FCM incidencia messages.
 */
public class GcmIncidRequestData extends GcmRequestData {

    private final long comunidadId;

    public GcmIncidRequestData(String typeMsg, long comunidadId)
    {
        super(typeMsg);
        this.comunidadId = comunidadId;
    }

    public long getComunidadId()
    {
        return comunidadId;
    }
}

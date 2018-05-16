package com.didekinlib.gcm.model.common;

/**
 * User: pedro@didekin
 * Date: 04/12/15
 * Time: 14:53
 */

public class GcmTokensHolder {

    private final String originalGcmTk;
    private final String newGcmTk;

    public GcmTokensHolder(String newGcmTk, String originalGcmTk)
    {
        this.newGcmTk = newGcmTk;
        this.originalGcmTk = originalGcmTk;
    }

    public String getNewGcmTk()
    {
        return newGcmTk;
    }

    public String getOriginalGcmTk()
    {
        return originalGcmTk;
    }
}
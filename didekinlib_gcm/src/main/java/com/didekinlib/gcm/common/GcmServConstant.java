package com.didekinlib.gcm.common;

/**
 * User: pedro@didekin
 * Date: 10/01/17
 * Time: 15:23
 */
@SuppressWarnings("unused")
public final class GcmServConstant {

    // Firebase gcm URL.
    public static final String FCM_HOST_PORT = "https://fcm.googleapis.com";
    // Path added to Firebase messaging base URL.
    public static final String FCM_PATH_REQUEST = "/fcm/send";

    // Http header parameter names.
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String AUTHORIZATION = "Authorization";
    // Http header parameter values.
    public static final String IDENTITY = "identity";
    public static final String GZIP = "gzip";

    private GcmServConstant()
    {
    }
}

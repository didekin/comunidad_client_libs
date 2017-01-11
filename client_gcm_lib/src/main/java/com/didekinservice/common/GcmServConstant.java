package com.didekinservice.common;

/**
 * User: pedro@didekin
 * Date: 10/01/17
 * Time: 15:23
 */
@SuppressWarnings("unused")
public final class GcmServConstant {

    // Server credentials.
    private static final String didekin_api_key =
            "AAAADknoTJQ:APA91bGRihWJup9TYYtKl6LV7d01f5DZJDr5edlwh8KV4fLzq8S20OYyxnqP7Hsj2b4B4zDU0G_jzDH8bOwXGlz77XMFzcPWnEZ8EcDqTbiNTSjDHxuegT2eE8Dsn9YvozF4GIbIHFaJ";
    public static final String didekin_package = "com.didekindroid";

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
    public static final String didekin_api_key_header = "key=" + didekin_api_key;

    private GcmServConstant()
    {
    }
}

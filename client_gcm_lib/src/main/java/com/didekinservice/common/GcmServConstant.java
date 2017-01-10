package com.didekinservice.common;

/**
 * User: pedro@didekin
 * Date: 10/01/17
 * Time: 15:23
 */
@SuppressWarnings("unused")
public final class GcmServConstant {


    // Path added to base URL.
    public static final String FCM_PATH_REQUEST = "/fcm/send";
    // Accept-encoding header.
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_ENCODING_IDENTITY = "identity";
    public static final String ACCEPT_ENCODING_GZIP = "gzip";
    // Authorization header.
    public static final String AUTHORIZATION_KEY = "Authorization:key";
    public static final String GCM_API_KEY =
            "AAAADknoTJQ:APA91bGRihWJup9TYYtKl6LV7d01f5DZJDr5edlwh8KV4fLzq8S20OYyxnqP7Hsj2b4B4zDU0G_jzDH8bOwXGlz77XMFzcPWnEZ8EcDqTbiNTSjDHxuegT2eE8Dsn9YvozF4GIbIHFaJ";

    private GcmServConstant()
    {
    }
}

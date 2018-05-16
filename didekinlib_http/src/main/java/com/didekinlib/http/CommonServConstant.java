package com.didekinlib.http;

/**
 * User: pedro@didekin
 * Date: 30/05/16
 * Time: 16:59
 */

public final class CommonServConstant {

    // Http headers.
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    // Mime types.
    public static final String MIME_JSON = "application/json";
    public static final String FORM_URLENCODED = "application/x-www-form-urlencoded";
    // Common paths out of the control of both authorization and resource servers.
    public static final String ERROR = "/error";

    private CommonServConstant()
    {
    }
}

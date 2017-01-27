package com.didekinlib.gcm.model.common;

/**
 * User: pedro@didekin
 * Date: 10/01/17
 * Time: 15:18
 */
@SuppressWarnings("unused")
public enum GcmErrorMessage {

    /**
     * Action: Check that the request contains a registration token (in the registration_id in a plain text message,
     * or in the to or registration_ids field in JSON).
     */
    MissingRegistration(200, "Missing registration token"),

    /**
     * Action: Check the format of the registration token you pass to the server.
     */
    InvalidRegistration(200, "Invalid registration token"),

    /**
     * An existing registration token may cease to be valid in a number of scenarios, including:
     * - If the client app unregisters with FCM.
     * - If the client app is automatically unregistered, which can happen if the user uninstalls the application.
     * - If the registration token expires (for example, Google might decide to refresh registration tokens).
     * - If the client app is updated but the new version is not configured to receive messages.
     * <p/>
     * Action: For all these cases, remove this registration token from the app server and stop using it to send messages.
     */
    NotRegistered(200, "Unregistered device"),

    /**
     * Action: Make sure the message was addressed to a registration token whose package name matches
     * the value passed in the request.
     */
    InvalidPackageName(200, "Invalid package name"),

    /**
     * Action: A registration token is tied to a certain group of senders. When a client app registers for FCM,
     * it must specify which senders are allowed to send messages. You should use one of those sender IDs when
     * sending messages to the client app. If you switch to a different sender, the existing registration
     * tokens won't work.
     */
    MismatchSenderId(200, "Mismatched sender ID"),

    /**
     * Action: Check that the payload data does not contain a key (such as from, or gcm, or any value prefixed
     * by google) that is used internally by FCM.
     */
    InvalidDataKey(200, "Invalid data key"),

    /**
     * Action: Check that the JSON message is properly formatted and contains valid fields.
     * The error message may contains the name of the wrong field.
     */
    InvalidJson(400, null),

    /**
     * The sender account used to send a message couldn't be authenticated.
     * Possible causes are:
     * - Authorization header missing or with invalid syntax in HTTP request.
     * - Invalid project number sent as key.
     * - Key valid but with FCM service disabled.
     * - Request originated from a server not whitelisted in the Server key IPs.
     * <p/>
     * Action: Check that the token you're sending inside the Authentication header is the correct Server key associated with your project.
     */
    Unauthorized(401, "Authentication error"),

    /**
     * Action: The server couldn't process the request in time.
     * Retry the same request, but you must:
     * - Honor the Retry-After header if it is included in the response from the FCM Connection Server.
     * - Implement exponential back-off in your retry mechanism. (e.g. if you waited one second before the first retry, wait at least
     * two second before the next one, then 4 seconds and so on). If you're sending multiple messages, delay each one independently
     * by an additional random amount to avoid issuing a new request for all messages at the same time.
     * <p/>
     * Senders that cause problems risk being blacklisted.
     */
    Unavailable(500, "Timeout"),  // TODO: implement 'retry-after' with exponential back-off.

    /**
     * Action: The server encountered an error while trying to process the request. You could retry the same
     * request following the requirements listed in "Unavailable"
     */
    InternalServerError(500, "Internal server error"),;

    public final String httpMessage;
    public final int httpStatusCode;
    public final String description;

    GcmErrorMessage(int httpStatusCode, String description)
    {
        httpMessage = this.name();
        this.httpStatusCode = httpStatusCode;
        this.description = description;
    }
}

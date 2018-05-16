package com.didekinlib.gcm.model.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: pedro@didekin
 * Date: 30/11/15
 * Time: 18:43
 */

public class GcmResponse {

    /**
     * Unique ID (number) identifying the multicast message.
     */
    private final long multicast_id;

    /**
     * Number of messages that were processed without an error.
     */
    private final int success;

    /**
     * Number of messages that could not be processed.
     */
    private final int failure;

    /**
     * Number of results that contain a canonical registration token.
     */
    private final int canonical_ids;

    /**
     * Array of objects representing the status of the messages processed. The objects are listed in the same order as the request
     * (i.e., for each registration ID in the request, its result is listed in the same index in the response).
     */
    private final Result[] results;

    /**
     * Pair of tokens (old and new one) to modify in the database with FCM tokens IDs.
     */
    private List<GcmTokensHolder> tokensToProcess;

    public GcmResponse(int canonical_ids, long multicast_id, int success, int failure, Result[] results)
    {
        this.canonical_ids = canonical_ids;
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.results = results;
    }

    public int getCanonical_ids()
    {
        return canonical_ids;
    }

    public int getFailure()
    {
        return failure;
    }

    public long getMulticast_id()
    {
        return multicast_id;
    }

    public Result[] getResults()
    {
        return results.clone();
    }

    public int getSuccess()
    {
        return success;
    }

    public List<GcmTokensHolder> getTokensToProcess()
    {
        return tokensToProcess.size() > 0 ? Collections.unmodifiableList(tokensToProcess) : tokensToProcess;
    }

    /**
     * Postconditions:
     * 1. A list of GcmTokenHolders is returned.
     * 2. If the GCM response result includes registration_id, both gcm tokens (old and new) are returned in the holder.
     * 3. If the result includes the error NotRegistered, the original gcm token is returned with a null value in the new token field.
     */
    public void setTokensToProcess(String[] gcmTokens)
    {
        if (gcmTokens.length > 0) {
            tokensToProcess = new ArrayList<>();
            for (int i = 0; i < results.length; i++) {
                if (results[i].getRegistration_id() != null) {
                    tokensToProcess.add(new GcmTokensHolder(results[i].getRegistration_id(), gcmTokens[i]));
                } else {
                    if (results[i].getError() != null && results[i].getError().equals(GcmErrorMessage.NotRegistered.httpMessage)) {
                        tokensToProcess.add(new GcmTokensHolder(null, gcmTokens[i]));
                    }
                }
            }
        } else {
            tokensToProcess = Collections.emptyList();
        }
    }

//    ==================================  INNER CLASSES ================================

    public static final class Result {

        /**
         * Message_id: String specifying a unique ID for each successfully processed message.
         */
        private final String message_id;

        /**
         * Registration_id: Optional string specifying the canonical registration token for the client app that the message was processed and sent to.
         * Sender should use this value as the registration token for future requests. Otherwise, the messages might be rejected.
         */
        private final String registration_id;

        /**
         * Error: String specifying the error that occurred when processing the message for the recipient.
         */
        private final String error;

        public Result(String error, String message_id, String registration_id)
        {
            this.error = error;
            this.message_id = message_id;
            this.registration_id = registration_id;
        }

        public String getError()
        {
            return error;
        }

        public String getMessage_id()
        {
            return message_id;
        }

        public String getRegistration_id()
        {
            return registration_id;
        }
    }

}

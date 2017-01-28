package com.didekinlib.gcm.model.common;

import com.didekinlib.model.common.dominio.BeanBuilder;

import java.util.List;

/**
 * User: pedro@didekin
 * Date: 02/06/16
 * Time: 09:12
 */
@SuppressWarnings("WeakerAccess")
public final class GcmMulticastRequest {

    /**
     * This parameter specifies a list of devices (registration tokens, or IDs) receiving a multicast message.
     * It must contain at least 1 and at most 1000 registration tokens.
     * Multicast messages (sending to more than 1 registration tokens) are allowed using HTTP JSON format only.
     * <p/>
     * A registration token is an ID generated by the FCM SDK for each client app instance.   registration_ids
     */
    final String[] registration_ids;
    final String priority;
    final boolean delay_while_idle;
    final int time_to_live;
    final String restricted_package_name;
    final String collapse_key;
    final GcmRequestData data;


    private GcmMulticastRequest(Builder builder)
    {
        registration_ids = builder.registration_ids;
        priority = builder.gcmRequest.priority;
        delay_while_idle = builder.gcmRequest.delay_while_idle;
        time_to_live = builder.gcmRequest.time_to_live;
        restricted_package_name = builder.gcmRequest.restricted_package_name;
        collapse_key = builder.gcmRequest.collapse_key;
        data = builder.gcmRequest.data;
    }

    public String[] getRegistration_ids()
    {
        return registration_ids;
    }

    //    ==================== BUILDER ====================

    @SuppressWarnings("unused")
    public static class Builder implements BeanBuilder<GcmMulticastRequest> {

        private final String[] registration_ids;
        private final GcmRequest gcmRequest;

        @Override
        public GcmMulticastRequest build()
        {
            return new GcmMulticastRequest(this);
        }

        public Builder(List<String> registration_ids, GcmRequest request)
        {
            this.registration_ids = registration_ids.toArray(new String[registration_ids.size()]);
            gcmRequest = request;
        }
    }
}

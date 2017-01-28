package com.didekinlib.gcm.model.common;

import com.didekinlib.model.common.dominio.BeanBuilder;

/**
 * User: pedro@didekin
 * Date: 02/06/16
 * Time: 13:07
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class GcmSingleRequest {

    /**
     * This parameter specifies the recipient of a message.
     * The value must be a registration token, notification key, or topic.
     */
    final String to;
    final GcmRequest gcmRequest;

    public GcmSingleRequest(Builder builder)
    {
        to = builder.to;
        gcmRequest = builder.gcmRequest;
    }

    //    ==================== BUILDER ====================

    public static class Builder implements BeanBuilder<GcmSingleRequest> {

        private final String to;
        private final GcmRequest gcmRequest;


        @Override
        public GcmSingleRequest build()
        {
            return new GcmSingleRequest(this);
        }

        public Builder(String to, GcmRequest gcmRequest)
        {
            this.to = to;
            this.gcmRequest = gcmRequest;
        }
    }
}

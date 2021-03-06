package com.didekinlib.gcm;


import com.didekinlib.BeanBuilder;

/**
 * User: pedro@didekin
 * Date: 02/06/16
 * Time: 12:44
 */

public class GcmRequest {

    /**
     * Sets the priority of the message. Valid values are "normal" and "high."
     * Default: normal.
     */
    final String priority;

    /**
     * When this parameter is set to true, it indicates that the message should not be sent until the device becomes active.
     * The default value is false.
     */
    final boolean delay_while_idle;

    /**
     * This parameter specifies how long (in seconds) the message should be kept in FCM storage if the device is offline.
     * The maximum time to live supported is 4 weeks.
     * Default: 48h.
     */
    final int time_to_live;

    /**
     * This parameter specifies the package name of the application where the registration tokens must match in order to receive the message.
     */
    final String restricted_package_name;

    /**
     * This parameter identifies a group of messages that can be collapsed, so that only the last message
     * gets sent when delivery can be resumed.
     * A maximum of 4 different collapse keys is allowed at any given time.
     * Default: the type of message.
     */
    final String collapse_key;

    /**
     * This parameter specifies the custom key-value pairs of the message's payload.
     */
    final GcmRequestData data;

    private GcmRequest(Builder builder)
    {
        data = builder.data;
        priority = builder.priority;
        time_to_live = builder.time_to_live;
        restricted_package_name = builder.restricted_package_name;
        collapse_key = builder.collapse_key;
        delay_while_idle = builder.delay_while_idle;
    }

    //    ==================== BUILDER ====================

    public static class Builder implements BeanBuilder<GcmRequest> {

        private final GcmRequestData data;
        private String priority;
        private int time_to_live;
        private String restricted_package_name;
        private String collapse_key;
        private boolean delay_while_idle = true;

        @Override
        public GcmRequest build()
        {
            if (priority == null) {
                priority = GcmRequestConstant.PRIORITY_NORMAL;
            }
            if (time_to_live == 0) {
                time_to_live = GcmRequestConstant.TIME_TO_LIVE_DEFAULT;
            }
            if (restricted_package_name == null) {
                throw new IllegalStateException("App package cannot be null");
            }
            if (collapse_key == null){
                collapse_key = data.getTypeMsg();
            }
            return new GcmRequest(this);
        }

        public Builder(GcmRequestData data, String appPackage)
        {
            this.data = data;
            restricted_package_name = appPackage;
        }

        public Builder priority(String initValue)
        {
            priority = initValue;
            return this;
        }

        public Builder collapseKey(String initValue)
        {
            collapse_key = initValue;
            return this;
        }

        public Builder timeToLive(int initValue)
        {
            time_to_live = initValue;
            return this;
        }

        public Builder delayIdle(boolean initValue)
        {
            delay_while_idle = initValue;
            return this;
        }
    }
}

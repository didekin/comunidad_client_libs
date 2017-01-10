package com.didekinservice.retrofit;

import com.didekinservice.common.GcmServConstant;
import com.didekinservice.common.gcm.GcmMulticastRequest;
import com.didekinservice.common.gcm.GcmResponse;
import com.didekinservice.common.gcm.GcmSingleRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.didekin.http.CommonServConstant.MIME_JSON;

/**
 * User: pedro@didekin
 * Date: 29/11/15
 * Time: 12:56
 */
@SuppressWarnings("unused")
public interface GcmEndPoint {

    @Headers({
            "Content-Type:" + MIME_JSON,
            "Authorization:key= " + GcmServConstant.GCM_API_KEY
    })
    @POST(GcmServConstant.FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmSingleRequest(@Body GcmSingleRequest singleRequest);

    @Headers({
            "Content-Type:" + MIME_JSON,
            "Authorization:key= " + GcmServConstant.GCM_API_KEY
    })
    @POST(GcmServConstant.FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmMulticastRequest(@Body GcmMulticastRequest multicastRequest);

    /**
     *  Overloaded version for tests: accept-encoding=identity.
     */
    @Headers({
            "Content-Type:" + MIME_JSON,
            "Authorization:key= " + GcmServConstant.GCM_API_KEY
    })
    @POST(GcmServConstant.FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmMulticastRequest(@Header(GcmServConstant.ACCEPT_ENCODING) String acceptEncoding,
                                              @Body GcmMulticastRequest multicastRequest);
}

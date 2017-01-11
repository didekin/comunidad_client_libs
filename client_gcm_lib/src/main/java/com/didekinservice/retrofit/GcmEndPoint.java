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
import static com.didekinservice.common.GcmServConstant.ACCEPT_ENCODING;
import static com.didekinservice.common.GcmServConstant.AUTHORIZATION;

/**
 * User: pedro@didekin
 * Date: 29/11/15
 * Time: 12:56
 */
public interface GcmEndPoint {

    @Headers({
            "Content-Type:" + MIME_JSON
    })
    @POST(GcmServConstant.FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmSingleRequest(@Header(ACCEPT_ENCODING) String acceptEncoding,
                                           @Header(AUTHORIZATION) String authorizationKey,
                                           @Body GcmSingleRequest singleRequest);

    @Headers({
            "Content-Type:" + MIME_JSON
    })
    @POST(GcmServConstant.FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmMulticastRequest(@Header(ACCEPT_ENCODING) String acceptEncoding,
                                              @Header(AUTHORIZATION) String authorizationKey,
                                              @Body GcmMulticastRequest multicastRequest);
}

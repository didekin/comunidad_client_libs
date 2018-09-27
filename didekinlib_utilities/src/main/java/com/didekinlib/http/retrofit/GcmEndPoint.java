package com.didekinlib.http.retrofit;

import com.didekinlib.gcm.GcmMulticastRequest;
import com.didekinlib.gcm.GcmResponse;
import com.didekinlib.gcm.GcmSingleRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.didekinlib.gcm.GcmServConstant.ACCEPT_ENCODING;
import static com.didekinlib.gcm.GcmServConstant.AUTHORIZATION;
import static com.didekinlib.gcm.GcmServConstant.FCM_PATH_REQUEST;
import static com.didekinlib.gcm.GcmServConstant.MIME_JSON;


/**
 * User: pedro@didekin
 * Date: 29/11/15
 * Time: 12:56
 */
public interface GcmEndPoint {

    @Headers({
            "Content-Type:" + MIME_JSON
    })
    @POST(FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmSingleRequest(@Header(ACCEPT_ENCODING) String acceptEncoding,
                                           @Header(AUTHORIZATION) String authorizationKey,
                                           @Body GcmSingleRequest singleRequest);

    @Headers({
            "Content-Type:" + MIME_JSON
    })
    @POST(FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmMulticastRequest(@Header(ACCEPT_ENCODING) String acceptEncoding,
                                              @Header(AUTHORIZATION) String authorizationKey,
                                              @Body GcmMulticastRequest multicastRequest);
}

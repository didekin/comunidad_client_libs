package com.didekinlib.gcm.retrofit;

import com.didekinlib.gcm.model.common.GcmServConstant;
import com.didekinlib.gcm.model.common.GcmMulticastRequest;
import com.didekinlib.gcm.model.common.GcmSingleRequest;
import com.didekinlib.gcm.model.common.GcmResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.didekinlib.http.CommonServConstant.MIME_JSON;

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
    Call<GcmResponse> sendGcmSingleRequest(@Header(GcmServConstant.ACCEPT_ENCODING) String acceptEncoding,
                                           @Header(GcmServConstant.AUTHORIZATION) String authorizationKey,
                                           @Body GcmSingleRequest singleRequest);

    @Headers({
            "Content-Type:" + MIME_JSON
    })
    @POST(GcmServConstant.FCM_PATH_REQUEST)
    Call<GcmResponse> sendGcmMulticastRequest(@Header(GcmServConstant.ACCEPT_ENCODING) String acceptEncoding,
                                              @Header(GcmServConstant.AUTHORIZATION) String authorizationKey,
                                              @Body GcmMulticastRequest multicastRequest);
}

package com.didekinservice.retrofit;

import com.didekin.http.ErrorBean;
import com.didekin.retrofit.RetrofitHandler;
import com.didekinservice.common.gcm.GcmException;
import com.didekinservice.common.gcm.GcmMulticastRequest;
import com.didekinservice.common.gcm.GcmResponse;
import com.didekinservice.common.gcm.GcmSingleRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static com.didekinservice.common.GcmServConstant.GZIP;
import static com.didekinservice.common.GcmServConstant.didekin_api_key_header;
import static com.didekinservice.common.gcm.GcmErrorMessage.InternalServerError;

/**
 * User: pedro@didekin
 * Date: 31/05/16
 * Time: 17:47
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class GcmEndPointImp implements GcmEndPoint {

    private final RetrofitHandler retrofitHandler;

    public GcmEndPointImp(RetrofitHandler retrofitHandler)
    {
        this.retrofitHandler = retrofitHandler;
    }

    @Override
    public Call<GcmResponse> sendGcmSingleRequest(String acceptEncoding, String authorizationKey, GcmSingleRequest singleRequest)
    {
        return retrofitHandler.getService(GcmEndPoint.class).sendGcmSingleRequest(acceptEncoding, authorizationKey, singleRequest);
    }

    @Override
    public Call<GcmResponse> sendGcmMulticastRequest(String acceptEncoding, String authorizationKey, GcmMulticastRequest multicastRequest)
    {
        return retrofitHandler.getService(GcmEndPoint.class).sendGcmMulticastRequest(acceptEncoding, authorizationKey, multicastRequest);
    }

//    ================================ CONVENIENCE METHODS ================================

    public GcmResponse sendMulticast(String acceptEncoding, String authorizationKey, GcmMulticastRequest multicastRequest)
            throws GcmException
    {
        GcmResponse gcmResponse;
        try {
            Response<GcmResponse> response = sendGcmMulticastRequest(acceptEncoding, authorizationKey, multicastRequest).execute();
            if (!response.isSuccessful()) {
                throw new GcmException(retrofitHandler.getErrorBean(response));
            }
            gcmResponse = response.body();
            gcmResponse.setTokensToProcess(multicastRequest.getRegistration_ids());
        } catch (IOException e) {
            throw new GcmException(new ErrorBean(e.getMessage(), InternalServerError.httpStatusCode));
        }
        return gcmResponse;
    }

    public GcmResponse sendMulticastGzip(String authorizationKey, GcmMulticastRequest multicastRequest)
            throws GcmException
    {
        return sendMulticast(GZIP, authorizationKey, multicastRequest);
    }

    public GcmResponse sendDidekinMulticastGzip(GcmMulticastRequest multicastRequest)
            throws GcmException
    {
        return sendMulticastGzip(didekin_api_key_header, multicastRequest);
    }
}

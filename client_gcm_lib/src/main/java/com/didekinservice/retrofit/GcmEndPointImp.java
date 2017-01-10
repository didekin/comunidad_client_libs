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

import static com.didekinservice.common.gcm.GcmErrorMessage.InternalServerError;

/**
 * User: pedro@didekin
 * Date: 31/05/16
 * Time: 17:47
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class GcmEndPointImp implements GcmEndPoint {

    private final RetrofitHandler retrofitHandler;

    public GcmEndPointImp(RetrofitHandler retrofitHandler)
    {
        this.retrofitHandler = retrofitHandler;
    }

    @Override
    public Call<GcmResponse> sendGcmSingleRequest(GcmSingleRequest singleRequest)
    {
        return retrofitHandler.getService(GcmEndPoint.class).sendGcmSingleRequest(singleRequest);
    }

    @Override
    public Call<GcmResponse> sendGcmMulticastRequest(GcmMulticastRequest multicastRequest)
    {
        return retrofitHandler.getService(GcmEndPoint.class).sendGcmMulticastRequest(multicastRequest);
    }

    @Override
    public Call<GcmResponse> sendGcmMulticastRequest(String acceptEncoding, GcmMulticastRequest multicastRequest)
    {
        return retrofitHandler.getService(GcmEndPoint.class).sendGcmMulticastRequest(acceptEncoding, multicastRequest);
    }

//    ================================ CONVENIENCE METHODS ================================

    public GcmResponse sendGcmMulticastRequestImp(GcmMulticastRequest multicastRequest)
            throws GcmException
    {
        GcmResponse gcmResponse;
        try {
            Response<GcmResponse> response = sendGcmMulticastRequest(multicastRequest).execute();
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
}

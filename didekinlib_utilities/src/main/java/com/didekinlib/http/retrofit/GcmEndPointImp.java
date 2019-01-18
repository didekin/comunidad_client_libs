package com.didekinlib.http.retrofit;

import com.didekinlib.gcm.GcmException;
import com.didekinlib.gcm.GcmMulticastRequest;
import com.didekinlib.gcm.GcmResponse;
import com.didekinlib.gcm.GcmSingleRequest;
import com.didekinlib.http.exception.ErrorBean;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static com.didekinlib.gcm.GcmErrorMessage.InternalServerError;
import static java.util.Objects.requireNonNull;

/**
 * User: pedro@didekin
 * Date: 31/05/16
 * Time: 17:47
 */

public class GcmEndPointImp implements GcmEndPoint {

    private final GcmRetrofitHandler retrofitHandler;

    public GcmEndPointImp(GcmRetrofitHandler retrofitHandler)
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
            requireNonNull(gcmResponse).setTokensToProcess(multicastRequest.getRegistration_ids());
        } catch (IOException e) {
            throw new GcmException(new ErrorBean(e.getMessage(), InternalServerError.httpStatusCode));
        }
        return gcmResponse;
    }

    public GcmResponse sendMulticastGzip(String authorizationKey, GcmMulticastRequest multicastRequest)
            throws GcmException
    {
        return sendMulticast(null, authorizationKey, multicastRequest);
    }
}

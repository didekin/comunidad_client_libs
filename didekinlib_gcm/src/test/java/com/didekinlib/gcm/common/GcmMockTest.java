package com.didekinlib.gcm.common;

import com.didekinlib.http.retrofit.RetrofitHandler;
import com.didekinlib.gcm.common.GcmResponse.Result;
import com.didekinlib.gcm.incidservice.GcmIncidRequestData;
import com.didekinlib.gcm.retrofit.GcmEndPointImp;
import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.didekinlib.model.incidencia.gcm.GcmKeyValueIncidData.incidencia_open_type;
import static com.didekinlib.gcm.common.GcmServConstant.IDENTITY;
import static com.didekinlib.gcm.common.GcmErrorMessage.InvalidRegistration;
import static com.didekinlib.gcm.common.GcmErrorMessage.NotRegistered;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 03/06/16
 * Time: 12:34
 */
public class GcmMockTest {

    private static final String didekin_package = "com.didekindroid";

    private static final String MSG_ID_1 = "msg_id_1";
    private static final String MSG_ID_3 = "msg_id_3";
    private static final String REGISTRATION_ID_1_A = "registration_id_1A";
    private static final String REGISTRATION_ID_1_B = "registration_id_1B";
    private static final String REGISTRATION_ID_2_A = "registration_id_2A";
    private static final String REGISTRATION_ID_3_A = "registration_id_3A";
    private static final String REGISTRATION_ID_3_B = "registration_id_3B";
    private static final String REGISTRATION_ID_4_A = "registration_id_4A";

    private MockWebServer server;
    private GcmEndPointImp endPointImp;
    private List<String> gcmTokens = new ArrayList<>();


    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable
        {
            server = new MockWebServer();
        }

        @Override
        protected void after()
        {
            try {
                server.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Before
    public void setUp() throws Exception
    {
        RetrofitHandler retrofitHandler = new RetrofitHandler(server.url("/mock/").toString(), 60);
        endPointImp = new GcmEndPointImp(retrofitHandler);
    }

    /**
     * Multicast with only one token.
     */
    @Test
    public void testMulticast_1() throws Exception
    {
        // Mock response.
        Result result1 = new Result(null, MSG_ID_1, REGISTRATION_ID_1_B);
        GcmResponse gcmResponseIn = new GcmResponse(1, 1001L, 1, 0, new Result[]{result1});
        String jsonResponse = new Gson().toJson(gcmResponseIn);
        server.enqueue(new MockResponse().setBody(jsonResponse));
        server.enqueue(new MockResponse().setBody(jsonResponse));

        gcmTokens.add(REGISTRATION_ID_1_A);
        GcmMulticastRequest request = new GcmMulticastRequest.Builder(gcmTokens,
                new GcmRequest.Builder(new GcmIncidRequestData(incidencia_open_type, 999L), didekin_package).build())
                .build();

        GcmResponse gcmResponse = endPointImp.sendMulticastGzip("apiKey_1", request);
        assertThat(gcmResponse.getSuccess(), is(1));
        assertThat(gcmResponse.getCanonical_ids(), is(1));
        assertThat(gcmResponse.getMulticast_id(), is(1001L));
        assertThat(gcmResponse.getTokensToProcess().get(0).getOriginalGcmTk(), is(REGISTRATION_ID_1_A));
        assertThat(gcmResponse.getTokensToProcess().get(0).getNewGcmTk(), is(REGISTRATION_ID_1_B));
    }

    /**
     * Multicast with 4 tokens.
     */
    @Test
    public void testMulticast_2() throws Exception
    {
        // Mock responses.
        Result result0 = new Result(InvalidRegistration.httpMessage, null, null);
        Result result1 = new Result(null, MSG_ID_1, null);
        Result result2 = new Result(null, MSG_ID_3, REGISTRATION_ID_3_B);
        Result result3 = new Result(NotRegistered.httpMessage, null, null);
        GcmResponse gcmResponseIn = new GcmResponse(2, 2002, 2, 2, new Result[]{result0, result1, result2, result3});
        String jsonResponse = new Gson().toJson(gcmResponseIn);
        server.enqueue(new MockResponse().setBody(jsonResponse));

        gcmTokens.add(REGISTRATION_ID_2_A);
        gcmTokens.add(REGISTRATION_ID_1_A);
        gcmTokens.add(REGISTRATION_ID_3_A);
        gcmTokens.add(REGISTRATION_ID_4_A);
        GcmMulticastRequest request = new GcmMulticastRequest.Builder(
                gcmTokens,
                new GcmRequest.Builder(new GcmIncidRequestData(incidencia_open_type, 999L), didekin_package)
                        .build())
                .build();

        GcmResponse gcmResponse = endPointImp.sendMulticast(IDENTITY, "apiKey_2", request);

        assertThat(gcmResponse.getResults().length, is(4));
        assertThat(gcmResponse.getSuccess(), is(2));
        assertThat(gcmResponse.getFailure(), is(2));

        // 1 success with new registration_id, to be updated in DB; 1 noRegistered failure, so the tokenId has to be deleted in DB.
        List<GcmTokensHolder> tokensHolders = gcmResponse.getTokensToProcess();
        assertThat(tokensHolders.size(), is(2));
        /* New tokenId:*/
        assertThat(tokensHolders.size(), is(2));
        assertThat(tokensHolders.get(0).getNewGcmTk(), is(REGISTRATION_ID_3_B));
        assertThat(tokensHolders.get(0).getOriginalGcmTk(), is(REGISTRATION_ID_3_A));
        assertThat(gcmResponse.getResults()[2].getError(), nullValue());
        /* Token to be deleted*/
        assertThat(tokensHolders.get(1).getOriginalGcmTk(), is(REGISTRATION_ID_4_A));
        assertThat(tokensHolders.get(1).getNewGcmTk(), nullValue());
        assertThat(gcmResponse.getResults()[3].getError(), is(NotRegistered.httpMessage));
    }
}
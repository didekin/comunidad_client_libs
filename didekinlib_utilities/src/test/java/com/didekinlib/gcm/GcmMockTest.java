package com.didekinlib.gcm;

import com.didekinlib.gcm.GcmResponse.Result;
import com.didekinlib.http.retrofit.GcmEndPointImp;
import com.didekinlib.http.retrofit.GcmRetrofitHandler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static com.didekinlib.gcm.GcmErrorMessage.InvalidRegistration;
import static com.didekinlib.gcm.GcmErrorMessage.NotRegistered;
import static com.didekinlib.gcm.GcmServConstant.IDENTITY;
import static com.didekinlib.json.MoshiUtil.toJsonStr;
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
        protected void before()
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
    public void setUp()
    {
        GcmRetrofitHandler retrofitHandler = new GcmRetrofitHandler(server.url("/mock/").toString(), 60);
        endPointImp = new GcmEndPointImp(retrofitHandler);
    }

    /**
     * Multicast with only one token.
     */
    @Test
    public void testMulticast_1()
    {
        // Mock response.
        Result result1 = new Result(null, MSG_ID_1, REGISTRATION_ID_1_B);
        GcmResponse gcmResponseIn = new GcmResponse(1, 1001L, 1, 0, new Result[]{result1});
        String jsonResponse = toJsonStr(gcmResponseIn);
        server.enqueue(new MockResponse().setBody(jsonResponse));
        server.enqueue(new MockResponse().setBody(jsonResponse));

        gcmTokens.add(REGISTRATION_ID_1_A);
        GcmMulticastRequest request = new GcmMulticastRequest.Builder(gcmTokens,
                new GcmRequest.Builder(new GcmRequestData("incidencia_open_type", 999L), didekin_package).build())
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
    public void testMulticast_2()
    {
        // Mock responses.
        Result result0 = new Result(InvalidRegistration.httpMessage, null, null);
        Result result1 = new Result(null, MSG_ID_1, null);
        Result result2 = new Result(null, MSG_ID_3, REGISTRATION_ID_3_B);
        Result result3 = new Result(NotRegistered.httpMessage, null, null);
        GcmResponse gcmResponseIn = new GcmResponse(2, 2002, 2, 2, new Result[]{result0, result1, result2, result3});
        server.enqueue(new MockResponse().setBody(toJsonStr(gcmResponseIn)));

        gcmTokens.add(REGISTRATION_ID_2_A);
        gcmTokens.add(REGISTRATION_ID_1_A);
        gcmTokens.add(REGISTRATION_ID_3_A);
        gcmTokens.add(REGISTRATION_ID_4_A);
        GcmMulticastRequest request = new GcmMulticastRequest.Builder(
                gcmTokens,
                new GcmRequest.Builder(new GcmRequestData("incidencia_open_type", 999L), didekin_package)
                        .build())
                .build();

        GcmResponse gcmResponse = endPointImp.sendMulticast(IDENTITY, "apiKey_2", request);

        assertThat(gcmResponse.getResults().length, is(4));
        assertThat(gcmResponse.getSuccess(), is(2));
        assertThat(gcmResponse.getFailure(), is(2));

        List<GcmTokensHolder> tokensHolders = gcmResponse.getTokensToProcess();
        assertThat(tokensHolders.size(), is(3));
        /*  1 invalidRegistration failure: Token to be deleted. */
        assertThat(tokensHolders.get(0).getOriginalGcmTk(), is(REGISTRATION_ID_2_A));
        assertThat(tokensHolders.get(0).getNewGcmTk(), nullValue());
        assertThat(gcmResponse.getResults()[0].getError(), is(InvalidRegistration.httpMessage));
        /*  1 success with new registration_id, to be updated in DB. */
        assertThat(tokensHolders.get(1).getNewGcmTk(), is(REGISTRATION_ID_3_B));
        assertThat(tokensHolders.get(1).getOriginalGcmTk(), is(REGISTRATION_ID_3_A));
        assertThat(gcmResponse.getResults()[2].getError(), nullValue());
        /*  1 noRegistered failure: Token to be deleted. */
        assertThat(tokensHolders.get(2).getOriginalGcmTk(), is(REGISTRATION_ID_4_A));
        assertThat(tokensHolders.get(2).getNewGcmTk(), nullValue());
        assertThat(gcmResponse.getResults()[3].getError(), is(NotRegistered.httpMessage));
    }
}

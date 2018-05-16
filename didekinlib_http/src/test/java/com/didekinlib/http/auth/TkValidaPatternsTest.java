package com.didekinlib.http.auth;

import org.junit.Test;

import static com.didekinlib.http.auth.TkValidaPatterns.tkEncrypted_direct_symmetricKey_REGEX;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * User: pedro@didekin
 * Date: 14/05/2018
 * Time: 15:35
 */
public class TkValidaPatternsTest {

    @Test
    public void test_IsPatternOk()
    {
        String realTkStr = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0." +
                "." +
                "5VwgpFR0MD9ROmsvMiZ9RQ.aZ0npeqHqAqlLFXDutloQ845AZ8OlJg9x0zT89wMRGrv" +
                "8WLIEQwerIFA9V4JfXrpMV1MxBQpc2jc8gGKRA1tZesffzkZ5TuYQn9Vyn21VYS9KbeF" +
                "d9qYzKS7vQxhK_fEpjCgpPGt8-mZF745ImHVJt2zClXfEeyjfK9Lr2izcuqF9gChc_I" +
                "VJGAOkfo7sv7fYMHNL4PWdWmZdIInmMnqGQ." +
                "Y9C0goJx2Qij0JeLmRV_pA";
        assertThat(tkEncrypted_direct_symmetricKey_REGEX.isPatternOk(realTkStr), is(true));
    }
}
package com.didekin.common.controller;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: pedro@didekin
 * Date: 13/05/16
 * Time: 11:46
 */
interface JksInClient {

    InputStream getInputStream() throws IOException;

    String getJksPswd();
}

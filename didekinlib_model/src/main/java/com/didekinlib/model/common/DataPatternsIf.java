package com.didekinlib.model.common;

/**
 * User: pedro@didekin
 * Date: 17/11/15
 * Time: 19:24
 */

public interface DataPatternsIf {

    boolean isPatternOk(String fieldToCheck);

    String getRegexp();
}

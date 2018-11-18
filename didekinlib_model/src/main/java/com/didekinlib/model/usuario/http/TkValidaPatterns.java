package com.didekinlib.model.usuario.http;

import com.didekinlib.model.common.DataPatternsIf;

import java.util.regex.Pattern;

import static com.didekinlib.http.CommonServConstant.OPEN;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.UNICODE_CASE;
import static java.util.regex.Pattern.compile;

/**
 * User: pedro@didekin
 * Date: 10/06/15
 * Time: 10:13
 */

public enum TkValidaPatterns implements DataPatternsIf {

    /**
     * Pattern: <header>.<encrypted key>.<initialization vector>.<ciphertext>.<authentication tag>, where encrypted key
     * is the empty String when the key management algorithm is DIRECT.
     */
    tkEncrypted_direct_symmetricKey_REGEX(".+\\.{2}.+\\..+"),
    /**
     * Pattern for paths which require token validation. It exclude the inital slash '/'.
     */
    closed_paths_REGEX("^(?!" + OPEN.substring(1) + ").*$");

    public static final String error_token_from_jsonStr = "Wrong initialization value in tokenFromJsonStr";

    private final Pattern pattern;
    private final String regexp;

    TkValidaPatterns(String patternString)
    {
        pattern = compile(patternString, UNICODE_CASE | CASE_INSENSITIVE);
        regexp = patternString;
    }

    @Override
    public boolean isPatternOk(String fieldToCheck)
    {
        return pattern.matcher(fieldToCheck).matches();
    }

    @Override
    public String getRegexp()
    {
        return regexp;
    }
}

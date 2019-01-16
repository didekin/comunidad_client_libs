package com.didekinlib.model.tx;

import java.io.Serializable;

/**
 * User: pedro@didekin
 * Date: 2019-01-10
 * Time: 19:03
 */
public interface TxRuleVerification extends Serializable {
    boolean verifyTx(Transaction transaction);
}

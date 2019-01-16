package com.didekinlib.model.tx;

import com.didekinlib.BeanBuilder;

import java.util.List;

import static com.didekinlib.json.MoshiUtil.toJsonStr;
import static java.util.Collections.emptyList;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 12:52
 */
public class Transaction<T extends TxState> {

    /**
     * It refers to type of logic to apply to the sha256PkNextSigner and ecSignatureTx fields.
     */
    private final List<? extends TxRuleVerification> txVerifyRules;
    /**
     * The list of states' primary keys modified by this transaction.
     */
    private final List<TxState.TxStateId> inputStateIds;
    /**
     * The new states produced by this transaction.
     */
    private final List<T> outputStates;
    /**
     * The sha256 hash of the "should be signer's" public key for this transaction to be valid.
     */
    private final byte[] sha256SignerPk;

    private Transaction(TxBuilder<T> builder)
    {
        txVerifyRules = builder.txVerifyRules;
        inputStateIds = builder.inputsIds;
        outputStates = builder.outputs;
        sha256SignerPk = builder.hashPk;
    }

    public List<? extends TxRuleVerification> getTxVerifyRules()
    {
        return txVerifyRules;
    }

    public List<TxState.TxStateId> getInputStateIds()
    {
        return inputStateIds == null ? emptyList() : inputStateIds;
    }

    public List<T> getOutputStates()
    {
        return outputStates;
    }

    public byte[] getSha256SignerPk()
    {
        return sha256SignerPk;
    }


    //    ==================== BUILDER ====================

    public static class TxBuilder<T extends TxState> implements BeanBuilder<Transaction<T>> {

        private List<? extends TxRuleVerification> txVerifyRules;
        private List<TxState.TxStateId> inputsIds;
        private List<T> outputs;
        private byte[] hashPk;

        public TxBuilder()
        {
        }

        TxBuilder version(List<TxRuleVerification> txRules)
        {
            txVerifyRules = txRules;
            return this;
        }

        TxBuilder inputsIds(List<TxState.TxStateId> inputsIdsIn)
        {
            inputsIds = inputsIdsIn;
            return this;
        }

        TxBuilder outputs(List<T> outputsIn)
        {
            outputs = outputsIn;
            return this;
        }

        TxBuilder hashPk(byte[] hashPkIn)
        {
            hashPk = hashPkIn;
            return this;
        }

        @Override
        public Transaction<T> build()
        {
            Transaction<T> transaction = new Transaction<>(this);
            boolean areRulesOk = true;
            for (TxRuleVerification rule : transaction.getTxVerifyRules()) {
                if (!rule.verifyTx(transaction)) {
                    areRulesOk = false;
                    break;
                }
            }
            if (txVerifyRules == null
                    || outputs == null
                    || outputs.size() == 0
                    || hashPk == null
                    || !areRulesOk) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return transaction;
        }
    }

    //    ============================= SERIALIZATION ==================================

    byte[] toJson()
    {
        return toJsonStr(this).getBytes();
    }
}

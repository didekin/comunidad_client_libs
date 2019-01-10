package com.didekinlib.model.tx;

import com.didekinlib.BeanBuilder;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 12:52
 */
public class Transaction<T extends TxState> {

    /**
     * It refers to type of logic to apply to the sha256PkNextSigner and  ecSignatureTx fields.
     */
    private final TxTypeByInOut txTypeVerifyCode;
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
        txTypeVerifyCode = builder.txTypeByInOut;
        inputStateIds = builder.inputsIds;
        outputStates = builder.outputs;
        sha256SignerPk = builder.hashPk;
    }

    byte[] toJson()
    {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Transaction> jsonAdapter = moshi.adapter(Transaction.class).nonNull();
        return jsonAdapter.toJson(this).getBytes();
    }

    public TxTypeByInOut getTxTypeVerifyCode()
    {
        return txTypeVerifyCode;
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

    // .............. Static classes ...........


    //    ==================== BUILDER ====================

    public static class TxBuilder<T extends TxState> implements BeanBuilder<Transaction> {

        private TxTypeByInOut txTypeByInOut;
        private List<TxState.TxStateId> inputsIds;
        private List<T> outputs;
        private byte[] hashPk;

        public TxBuilder()
        {
        }

        TxBuilder version(TxTypeByInOut txTypeByInOutIn)
        {
            txTypeByInOut = txTypeByInOutIn;
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
        public Transaction build()
        {
            Transaction transaction = new Transaction<>(this);
            if (txTypeByInOut == null
                    || outputs == null
                    || outputs.size() == 0
                    || hashPk == null
                    || !transaction.txTypeVerifyCode.verifyInputOutput(transaction)) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return transaction;
        }
    }
}

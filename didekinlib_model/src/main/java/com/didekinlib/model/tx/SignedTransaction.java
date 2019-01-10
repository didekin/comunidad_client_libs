package com.didekinlib.model.tx;

import com.didekinlib.BeanBuilder;
import com.didekinlib.crypto.Sha256Worker;
import com.didekinlib.crypto.Sha256WorkerIf;

import java.security.PublicKey;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 17:33
 */
public class SignedTransaction<T extends TxState, E extends PublicKey> {

    private final Transaction<T> tx;

    /**
     * The id of this transaction: sha256 hash of ...
     * - txTypeVerification
     * - inputSateIds
     * - outputStates
     * - sha256PkNextSigner
     */
    private final byte[] txId;

    /**
     *   The actual EC signature of the transaction.
     */
    private final byte[] signatureTx;

   /**
     *  Actual public key used for the transaction signature.
     */
    private final E pkForVerifying;

    public SignedTransaction(SignedTxBuilder<T,E> builder)
    {
        tx = builder.transaction;
        signatureTx = builder.signature;
        pkForVerifying = builder.pkForVerifying;
        txId = builder.txId;
    }

    public byte[] getTxId()
    {
        return txId;
    }

    //    ==================== BUILDER ====================

    public static class SignedTxBuilder<T extends TxState, E extends PublicKey> implements BeanBuilder<SignedTransaction> {

        private Transaction<T> transaction;
        private byte[] signature;
        private E pkForVerifying;
        private byte[] txId;
        private final Sha256WorkerIf sha256Worker = new Sha256Worker();

        public SignedTxBuilder(Transaction<T> transaction)
        {
            this.transaction = transaction;
        }

        public SignedTxBuilder signature(byte[] signatureIn){
            signature = signatureIn;
            return this;
        }

        public SignedTxBuilder privateKey(E privateKeyIn){
            pkForVerifying = privateKeyIn;
            return this;
        }

        SignedTxBuilder txId(byte[] txIdIn)
        {
            txId = txIdIn;
            return this;
        }

        @Override
        public SignedTransaction build()
        {
            SignedTransaction<T,E> signedTx = new SignedTransaction<>(this);
            if (txId == null || !sha256Worker.verifySha256(transaction.toJson(), txId)) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return signedTx;
        }
    }
}

package com.didekinlib.model.tx;

import java.io.Serializable;
import java.util.Arrays;

import static com.didekinlib.BeanBuilder.error_message_bean_building;

/**
 * User: pedro@didekin
 * Date: 2018-12-17
 * Time: 13:03
 */
public interface TxState extends Serializable {

    LifeCycleEnum getLifeCycle();

    TxStateId getTxStateId();

    class TxStateId {
        /**
         * Hash of the transaction in which the outputs list, where this state is included, was created.
         */
        private final byte[] txId;

        /**
         * Order in the outputs list of this state; it begins at 0.
         */
        private final int orderInTx;


        public TxStateId(byte[] txIdIn, int orderInTx)
        {
            if (txIdIn == null){
                throw new IllegalArgumentException(error_message_bean_building + this.getClass().getName());
            }
            this.txId = txIdIn;
            this.orderInTx = orderInTx;

        }

        public byte[] getTxId()
        {
            return txId;
        }

        public int getOrderInTx()
        {
            return orderInTx;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof TxStateId)) {
                return false;
            }
            TxStateId txStateIdIn = (TxStateId) obj;
            return Arrays.equals(txId, txStateIdIn.txId) && orderInTx == txStateIdIn.orderInTx;
        }

        @Override
        public int hashCode()
        {
            return Arrays.hashCode(txId) + orderInTx;
        }
    }

    /**
     *  Simple life cycle implemented as a couple of string values in DB:
     *  - op: open.
     *  - cl: close.
     */
    enum LifeCycleEnum {
        op,
        cl,
        ;
    }
}

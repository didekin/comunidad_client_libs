package com.didekinlib.model.tx;

/**
 * User: pedro@didekin
 * Date: 2018-12-20
 * Time: 18:09
 * <p>
 * Enum to classify the transactions, after the number of inputs and outputs in them.
 */
enum TxTypeByInOut {
    zero_in_one_out(0, 1) {
        @Override
        boolean verifyInputOutput(Transaction tx)
        {
            return (tx.getInputStateIds().size() == numberInputs)
                    && (tx.getOutputStates().size() == numberOutputs);
        }
    },
    zero_in_several_out(0, 2) {
        @Override
        boolean verifyInputOutput(Transaction tx)
        {
            return (tx.getInputStateIds().size() == numberInputs)
                    && (tx.getOutputStates().size() >= numberOutputs);
        }
    },
    one_in_one_out(1, 1) {
        @Override
        boolean verifyInputOutput(Transaction tx)
        {
            return (tx.getInputStateIds().size() == numberInputs)
                    && (tx.getOutputStates().size() == numberOutputs);
        }
    },
    one_in_several_out(1, 2) {
        @Override
        boolean verifyInputOutput(Transaction tx)
        {
            return (tx.getInputStateIds().size() == numberInputs)
                    && (tx.getOutputStates().size() >= numberOutputs);
        }
    },
    several_in_one_out(2, 1) {
        @Override
        boolean verifyInputOutput(Transaction tx)
        {
            return (tx.getInputStateIds().size() >= numberInputs)
                    && (tx.getOutputStates().size() == numberOutputs);
        }
    },
    several_in_several_out(2, 2) {
        @Override
        boolean verifyInputOutput(Transaction tx)
        {
            return (tx.getInputStateIds().size() >= numberInputs)
                    && (tx.getOutputStates().size() >= numberOutputs);
        }
    },
    ;

    final int numberInputs, numberOutputs;

    TxTypeByInOut(int numIns, int numOuts)
    {
        numberInputs = numIns;
        numberOutputs = numOuts;
    }

    public int getNumberInputs()
    {
        return numberInputs;
    }

    public int getNumberOutputs()
    {
        return numberOutputs;
    }

    abstract boolean verifyInputOutput(Transaction transaction);
}
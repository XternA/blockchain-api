package info.blockchain.handler;

import info.blockchain.transaction.Transaction;
import lombok.Getter;

import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@Named
@Getter
public final class TransactionHandler {

    private final List<Transaction> outputs = new LinkedList<>();

    public void addUnspentTransaction(Transaction transaction) {
        outputs.add(transaction);
    }

    public void clearTransactionOutputs() {
        outputs.clear();
    }
}

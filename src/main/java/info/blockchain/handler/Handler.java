package info.blockchain.handler;

import info.blockchain.client.HttpClient;
import info.blockchain.data.Transaction;
import info.blockchain.data.Transaction.TransactionBuilder;
import info.blockchain.data.UnspentTransaction;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@AllArgsConstructor
public class Handler implements IHandler {

    private final HttpClient httpClient;
    private final TransactionHandler transactionHandler;

    @Override
    public final TransactionHandler getUnspentTransactions(String walletAddress) {
        List<UnspentTransaction> unspentTransactions = httpClient.getUnspentTransactions(walletAddress);
        TransactionBuilder transactionBuilder = Transaction.builder();

        unspentTransactions.forEach(unspentTransaction -> transactionHandler
                .addUnspentTransaction(
                        transactionBuilder
                                .value(unspentTransaction.getValue())
                                .tx_hash(unspentTransaction.getTxHash())
                                .output_idx(unspentTransaction.getTxOutputNo())
                                .build()
                )
        );
        return transactionHandler;
    }
}

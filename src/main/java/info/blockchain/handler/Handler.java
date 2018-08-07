package info.blockchain.handler;

import info.blockchain.client.BlockchainExplorerClient;
import info.blockchain.exception.CustomException;
import info.blockchain.transaction.Transaction;
import info.blockchain.transaction.Transaction.TransactionBuilder;
import info.blockchain.transaction.UnspentTransaction;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@AllArgsConstructor
public class Handler implements IHandler {

    private final BlockchainExplorerClient blockchainExplorerClient;
    private final TransactionHandler transactionHandler;

    @Override
    public final TransactionHandler getUnspentTransactions(String walletAddress) throws CustomException {
        mapTransactions(blockchainExplorerClient.getUnspentTransactions(walletAddress), Transaction.builder());
        return transactionHandler;
    }

    private void mapTransactions(List<UnspentTransaction> unspentTransactions, TransactionBuilder transactionBuilder) {
        transactionHandler.clearTransactionOutputs();
        unspentTransactions.forEach(unspentTransaction -> transactionHandler
                .addUnspentTransaction(
                        transactionBuilder
                                .setValue(unspentTransaction.getValue())
                                .setTxHash(unspentTransaction.getTx_hash())
                                .setOutputIdx(unspentTransaction.getTx_output_n())
                                .build()
                )
        );
    }
}

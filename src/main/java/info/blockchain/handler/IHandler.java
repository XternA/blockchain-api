package info.blockchain.handler;

public interface IHandler {

    TransactionHandler getUnspentTransactions(String walletAddress);
}

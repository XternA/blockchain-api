package info.blockchain.handler;

import info.blockchain.exception.CustomException;

public interface IHandler {

    TransactionHandler getUnspentTransactions(String walletAddress) throws CustomException;
}

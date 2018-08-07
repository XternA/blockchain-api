package info.blockchain.handler;

import info.blockchain.exception.RequestException;
import info.blockchain.list.TransactionList;

public interface ITransactionHandler {

    /**
     * Method to get all unspent transactions of the given wallet address.
     *
     * @param walletAddress Bitcoin wallet address.
     * @return A TransactionList object which holds a list of all the unspent transaction for the given wallet address.
     * @throws RequestException Throws exception when there's something wrong with the request.
     */
    TransactionList getUnspentTransactions(String walletAddress) throws RequestException;
}

package info.blockchain.client;

import com.google.gson.Gson;
import info.blockchain.exception.RequestException;
import info.blockchain.list.UnspentTransactionList;
import info.blockchain.transaction.UnspentTransaction;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.List;

@Named
@AllArgsConstructor
public final class BlockchainExplorerClient {

    private final RestTemplate restTemplate;
    private final Gson jsonConverter;

    public List<UnspentTransaction> getUnspentTransactions(String walletAddress) throws RequestException {
        try {
            final String jsonResponse = restTemplate.getForObject("https://blockchain.info/unspent?active=" + walletAddress, String.class);
            return jsonConverter.fromJson(jsonResponse, UnspentTransactionList.class).getUnspentOutputs();
        } catch (HttpServerErrorException e) {
            throw new RequestException("Something went wrong. Please contact Blockchain.com for further assistance.");
        }
    }
}

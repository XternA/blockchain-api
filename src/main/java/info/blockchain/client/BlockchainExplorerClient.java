package info.blockchain.client;

import com.google.gson.Gson;
import info.blockchain.exception.CustomException;
import info.blockchain.handler.UnspentTransactionHandler;
import info.blockchain.transaction.UnspentTransaction;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public final class BlockchainExplorerClient {

    private final RestTemplate restTemplate;
    private final Gson jsonConverter;

    @Inject
    public BlockchainExplorerClient(RestTemplate restTemplate, Gson jsonConverter) {
        this.restTemplate = restTemplate;
        this.jsonConverter = jsonConverter;
    }

    public List<UnspentTransaction> getUnspentTransactions(String walletAddress) throws CustomException {
        try {
            final String jsonResponse = restTemplate.getForObject("https://blockchain.info/unspent?active=" + walletAddress, String.class);
            return jsonConverter.fromJson(jsonResponse, UnspentTransactionHandler.class).getUnspentOutputs();
        } catch (Exception e) {
            throw new CustomException("Something went wrong. Please contact Blockchain.com for further assistance.");
        }
    }
}

package info.blockchain.client;

import com.google.gson.Gson;
import info.blockchain.data.UnspentTransaction;
import info.blockchain.handler.UnspentTransactionHandler;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class HttpClient {

    private final RestTemplate restTemplate;
    private final Gson jsonConverter;

    @Inject
    public HttpClient(RestTemplate restTemplate, Gson jsonConverter) {
        this.restTemplate = restTemplate;
        this.jsonConverter = jsonConverter;
    }

    public List<UnspentTransaction> getUnspentTransactions(String walletAddress) {
        final String jsonResponse = restTemplate.getForObject("https://blockchain.info/unspent?active=" + walletAddress, String.class);
        return jsonConverter.fromJson(jsonResponse, UnspentTransactionHandler.class).getUnspentOutputs();
    }
}

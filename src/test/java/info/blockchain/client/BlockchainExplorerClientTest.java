package info.blockchain.client;

import com.google.gson.Gson;
import info.blockchain.exception.RequestException;
import info.blockchain.helper.RawJsonStringLoaderHelper;
import info.blockchain.helper.UnspentTransactionHelper;
import info.blockchain.transaction.UnspentTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlockchainExplorerClientTest {

    private BlockchainExplorerClient blockchainExplorerClient;
    private String walletAddress;
    private List<UnspentTransaction> expectedUnspentTransactions;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        blockchainExplorerClient = new BlockchainExplorerClient(restTemplate, new Gson());

        walletAddress = "1Aff4FgrtA1dZDwajmknWTwU2WtwUvfiXa";
        expectedUnspentTransactions = UnspentTransactionHelper.getUnspentTransactions();
    }

    @Test
    void getUnspentTransactions() throws Exception {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(RawJsonStringLoaderHelper.getRawJsonStringResponse("raw-unspent-transaction-json-response"));
        List<UnspentTransaction> unspentTransactions = blockchainExplorerClient.getUnspentTransactions(walletAddress);

        verify(restTemplate, times(1)).getForObject(anyString(), any());

        assertThat(unspentTransactions, notNullValue());
        assertThat(unspentTransactions.size(), is(3));

        IntStream.range(0, 3).forEach(index -> {
            UnspentTransaction result = unspentTransactions.get(index);
            UnspentTransaction expected = expectedUnspentTransactions.get(index);

            assertThat(result.getTx_hash(), is(expected.getTx_hash()));
            assertThat(result.getTx_hash_big_endian(), is(expected.getTx_hash_big_endian()));
            assertThat(result.getTx_index(), is(expected.getTx_index()));
            assertThat(result.getTx_output_n(), is(expected.getTx_output_n()));
            assertThat(result.getScript(), is(expected.getScript()));
            assertThat(result.getValue(), is(expected.getValue()));
            assertThat(result.getValue_hex(), is(expected.getValue_hex()));
            assertTrue(result.getConfirmations() > 0);
        });
    }

    @Test
    void getUnspentTransactions_ThrowsRequestException() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(HttpServerErrorException.class);
        assertThrows(RequestException.class, () -> blockchainExplorerClient.getUnspentTransactions(walletAddress));
        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }

    @Test
    void getUnspentTransactions_ConvertJson_ThrowsRequestException() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn("");
        assertThrows(RequestException.class, () -> blockchainExplorerClient.getUnspentTransactions(walletAddress));
    }
}
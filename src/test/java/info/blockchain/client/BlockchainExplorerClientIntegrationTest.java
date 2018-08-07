package info.blockchain.client;

import com.google.gson.Gson;
import info.blockchain.helper.UnspentTransactionHelper;
import info.blockchain.transaction.UnspentTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockchainExplorerClientIntegrationTest {

    private BlockchainExplorerClient blockchainExplorerClient;
    private List<UnspentTransaction> expectedUnspentTransactions;

    @BeforeEach
    void setUp() {
        blockchainExplorerClient = new BlockchainExplorerClient(new RestTemplate(), new Gson());
        expectedUnspentTransactions = UnspentTransactionHelper.getUnspentTransctions();
    }

    @Test
    void getUnspentTransactions() throws Exception {
        List<UnspentTransaction> unspentTransactions = blockchainExplorerClient.getUnspentTransactions("1NNKN1zQnjXnGLJiZmYMmaB74Uy2D6X5Ug");

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
}
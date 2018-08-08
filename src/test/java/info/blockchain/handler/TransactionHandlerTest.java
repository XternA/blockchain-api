package info.blockchain.handler;

import info.blockchain.client.BlockchainExplorerClient;
import info.blockchain.helper.UnspentTransactionHelper;
import info.blockchain.list.TransactionList;
import info.blockchain.transaction.Transaction;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionHandlerTest {

    @InjectMocks
    private TransactionHandler transactionHandler;
    @Mock
    private BlockchainExplorerClient blockchainExplorerClient;

    @BeforeEach
    void setUp() throws Exception {
        when(blockchainExplorerClient.getUnspentTransactions(anyString()))
                .thenReturn(UnspentTransactionHelper.getUnspentTransactions());
    }

    @Test
    void getUnspentTransactions() throws Exception {
        TransactionList unspentTransactions = transactionHandler.getUnspentTransactions("1Aff4FgrtA1dZDwajmknWTwU2WtwUvfiXa");

        assertThat(unspentTransactions.getOutputs(), IsNull.notNullValue());
        assertThat(unspentTransactions.getOutputs().size(), is(3));

        List<Transaction> result = unspentTransactions.getOutputs();

        Transaction transaction = result.get(0);
        assertThat(transaction.getValue(), is(1685235L));
        assertThat(transaction.getTx_hash(), is("b94313cb3d8ce36ad270d43fd155111a2495f43d8d156f9b570448d22f3dc739"));
        assertThat(transaction.getOutput_idx(), is(13L));

        transaction = result.get(1);
        assertThat(transaction.getValue(), is(2865979L));
        assertThat(transaction.getTx_hash(), is("946053b712c163165ff098ea1a7567856dccba3c0c17d9c4ad8dfaa1d3f2d271"));
        assertThat(transaction.getOutput_idx(), is(19L));

        transaction = result.get(2);
        assertThat(transaction.getValue(), is(476325L));
        assertThat(transaction.getTx_hash(), is("32c2cf2adae996c628aa1a62d5975b069d9fa9263f48a0bf6aa09bce0e952852"));
        assertThat(transaction.getOutput_idx(), is(79L));
    }
}
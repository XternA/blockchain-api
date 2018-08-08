package info.blockchain.controller;

import info.blockchain.exception.IncorrectAddressException;
import info.blockchain.exception.RequestException;
import info.blockchain.handler.ITransactionHandler;
import info.blockchain.list.TransactionList;
import info.blockchain.transaction.Transaction;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestControllerTest {

    @InjectMocks
    private RequestController requestController;

    @Mock
    private ITransactionHandler transactionHandler;

    @Test
    void getUnspentTransaction_AllValidParameters() throws Exception {
        Transaction transaction = Transaction.builder()
                .setValue(121515)
                .setTxHash("b94313cb3d8ce36ad270d43fd155111a2495f43d8d156f9b570448d22f3dc739")
                .setOutputIdx(4)
                .build();

        when(transactionHandler.getUnspentTransactions("1NNKN1zQnjXnGLJiZmYMmaB74Uy2D6X5Ug"))
                .thenReturn(TransactionList.builder().outputs(Collections.singletonList(transaction)).build());

        ResponseEntity<?> unspentTransaction = requestController.getUnspentTransaction("1NNKN1zQnjXnGLJiZmYMmaB74Uy2D6X5Ug");
        TransactionList transactionList = (TransactionList) unspentTransaction.getBody();

        assertThat(unspentTransaction.getStatusCode(), is(HttpStatus.OK));
        assertThat(transactionList.getOutputs().size(), is(1));

        assertThat(transaction, IsEqual.equalTo(transaction));
        assertThat(transactionList.getOutputs().get(0).getValue(), is(transaction.getValue()));
        assertThat(transactionList.getOutputs().get(0).getTx_hash(), is(transaction.getTx_hash()));
        assertThat(transactionList.getOutputs().get(0).getOutput_idx(), is(transaction.getOutput_idx()));
    }

    @Test
    void getUnspentTransaction_ValidAddress_NoContent() throws Exception {
        when(transactionHandler.getUnspentTransactions(any())).thenThrow(RequestException.class);
        assertThrows(RequestException.class, () -> requestController.getUnspentTransaction("1Aff4FgrtA1dZDwajmknWTwU2WtwUvfiXa"));
    }

    @Test
    void getUnspentTransaction_InvalidAddress_LengthGreaterThan() {
        assertThrows(IncorrectAddressException.class, () -> requestController.getUnspentTransaction("1NNKN1zQnjXnGLJiZmYMmaB74Uy2D6X5U1dfr352"));
    }

    @Test
    void getUnspentTransaction_InvalidAddress_LengthLessThan() {
        assertThrows(IncorrectAddressException.class, () -> requestController.getUnspentTransaction("1NNKN1zQnjXnGLJi"));
    }

    @Test
    void getUnspentTransaction_NoAddressSupplied() {
        assertThrows(IncorrectAddressException.class, () -> requestController.getUnspentTransaction(""));
    }
}
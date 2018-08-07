package info.blockchain.controller;

import info.blockchain.exception.IncorrectAddressException;
import info.blockchain.handler.ITransactionHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestController {

    private final ITransactionHandler transactionHandler;

    @GetMapping(value = "/address/{wallet}")
    @ResponseBody
    public final ResponseEntity<?> getUnspentTransaction(@PathVariable(value = "wallet") String walletAddress) throws Exception {
        checkAddressLength(walletAddress);
        return new ResponseEntity<>(transactionHandler.getUnspentTransactions(walletAddress), HttpStatus.OK);
    }

    private void checkAddressLength(String walletAddress) throws IncorrectAddressException {
        if (walletAddress.length() < 26 || walletAddress.length() > 34) {
            throw new IncorrectAddressException("Incorrect address provided. Please try again.");
        }
    }
}

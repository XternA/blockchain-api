package info.blockchain.controller;

import info.blockchain.exception.IncorrectAddressException;
import info.blockchain.handler.ITransactionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class RequestController {

    private static final int WALLET_LENGTH_LOWER_LIMIT = 26;
    private static final int WALLET_LENGTH_UPPER_LIMIT = 34;

    private final ITransactionHandler transactionHandler;

    @GetMapping(value = "/address/{wallet}")
    @ResponseBody
    public final ResponseEntity<?> getUnspentTransaction(@PathVariable(value = "wallet") String walletAddress) throws Exception {
        final long startTime = System.nanoTime();

        try {
            validateAddressLength(walletAddress);
            return new ResponseEntity<>(transactionHandler.getUnspentTransactions(walletAddress), HttpStatus.OK);
        } finally {
            log.info("Time taken for response took " + ((System.nanoTime() - startTime) / 1_000_000L) + " ms");
        }
    }

    private void validateAddressLength(String walletAddress) throws IncorrectAddressException {
        if (walletAddress.length() < WALLET_LENGTH_LOWER_LIMIT || walletAddress.length() > WALLET_LENGTH_UPPER_LIMIT) {
            throw new IncorrectAddressException("Incorrect address provided. Please try again.");
        }
    }
}

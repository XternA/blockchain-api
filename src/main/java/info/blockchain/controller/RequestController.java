package info.blockchain.controller;

import info.blockchain.handler.IHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public final class RequestController {

    private final IHandler handler;

    @Inject
    public RequestController(IHandler handler) {
        this.handler = handler;
    }

    @GetMapping(value = "/address")
    @ResponseBody
    public final ResponseEntity<?> getUnspentTransaction(@RequestParam(value = "wallet") String walletAddress) {
        return new ResponseEntity<>(handler.getUnspentTransactions(walletAddress), HttpStatus.OK);
    }
}

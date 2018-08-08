package info.blockchain.main;

import lombok.extern.slf4j.Slf4j;
import module.AppModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BlockchainRestEngine {

    public static void main(String[] args) {
        SpringApplication.run(AppModule.class, args);
        log.info("Blockchain REST Endpoint Started");
    }
}

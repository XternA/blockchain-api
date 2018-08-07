package info.blockchain.main;

import module.AppModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestEngine {

    public static void main(String[] args) {
        SpringApplication.run(AppModule.class, args);
    }
}

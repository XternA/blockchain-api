package module;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("info.blockchain")
public class AppModule {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    Gson jsonConverter() {
        return new Gson();
    }
}
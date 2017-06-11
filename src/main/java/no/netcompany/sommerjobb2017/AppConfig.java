package no.netcompany.sommerjobb2017;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final List<SparkService> services) {
        return args -> services.forEach(SparkService::init);
    }
}

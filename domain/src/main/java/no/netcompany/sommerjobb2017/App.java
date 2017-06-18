package no.netcompany.sommerjobb2017;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableOAuth2Sso
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

package no.netcompany.sommerjobb2017;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class App extends WebSecurityConfigurerAdapter {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}

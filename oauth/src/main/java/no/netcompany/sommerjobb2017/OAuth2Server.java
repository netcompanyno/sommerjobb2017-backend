package no.netcompany.sommerjobb2017;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAuthorizationServer
public class OAuth2Server {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2Server.class, args);
    }

    @Autowired
    public void configure(final AuthenticationManagerBuilder auth,
                          final PasswordEncoder passwordEncoder,
                          final DataSource dataSource) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("" +
                        "SELECT u.email AS username, role AS authority " +
                        "FROM user_role ur " +
                        "  JOIN \"user\" u ON u.id = ur.user_id " +
                        "WHERE u.email = ?")
                .usersByUsernameQuery("" +
                        "SELECT " +
                        "  email         AS username, " +
                        "  password_hash AS password, " +
                        "  enabled " +
                        "FROM \"user\" " +
                        "WHERE email = ?");
    }

    @Bean
    public TokenStore tokenStore(final JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

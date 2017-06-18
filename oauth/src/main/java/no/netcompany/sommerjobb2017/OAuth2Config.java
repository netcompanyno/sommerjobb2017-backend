package no.netcompany.sommerjobb2017;

import no.netcompany.sommerjobb2017.user.User;
import no.netcompany.sommerjobb2017.user.UserDao;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private final Environment environment;
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    public OAuth2Config(final Environment environment,
                        final AuthenticationManager authenticationManager,
                        final UserDao userDao) {
        Objects.requireNonNull(userDao);
        this.userDao = userDao;
        Objects.requireNonNull(authenticationManager);
        this.authenticationManager = authenticationManager;
        Objects.requireNonNull(environment);
        this.environment = environment;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        final JwtAccessTokenConverter tokenConverter = jwtAccessTokenConverter(environment);
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(
                customTokenEnhancer(userDao),
                tokenConverter));

        endpoints.tokenStore(tokenStore(tokenConverter))
                .accessTokenConverter(tokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public ClientDetailsService clientDetailsService(final BaseClientDetails baseClientDetails) {
        final InMemoryClientDetailsService clientDetailsService = new InMemoryClientDetailsService();
        clientDetailsService.setClientDetailsStore(
                Collections.singletonMap(baseClientDetails.getClientId(), baseClientDetails));
        return clientDetailsService;
    }

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public BaseClientDetails oauth2ClientDetails() {
        return new BaseClientDetails();
    }

    @Bean
    public TokenStore tokenStore(final JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(final Environment environment) {
        final String password = environment.getProperty("keystore.password");
        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("jwt.jks"),
                password.toCharArray());
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }

    @Bean
    public TokenEnhancer customTokenEnhancer(final UserDao userDao) {
        return ((accessToken, authentication) -> {
            final User user = userDao.getByEmail(authentication.getName());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
                    Collections.singletonMap("full_name", user.getName()));
            return accessToken;
        });
    }
}

package no.netcompany.sommerjobb2017;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.Filter;
import java.util.Objects;

@SpringBootApplication
// Uncomment to enable @Secured for authorization based on authorities (roles).
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@EnableOAuth2Sso
public class App extends WebSecurityConfigurerAdapter {
    private final OAuth2ClientContext clientContext;
    private final AuthorizationCodeResourceDetails oauth2Client;
    private final ResourceServerTokenServices resourceServerTokenServices;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public App(final OAuth2ClientContext clientContext,
               final AuthorizationCodeResourceDetails oauth2Client,
               final ResourceServerTokenServices resourceServerTokenServices,
               final AuthenticationSuccessHandler authenticationSuccessHandler) {
        Objects.requireNonNull(authenticationSuccessHandler);
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        Objects.requireNonNull(resourceServerTokenServices);
        this.resourceServerTokenServices = resourceServerTokenServices;
        Objects.requireNonNull(oauth2Client);
        this.oauth2Client = oauth2Client;
        Objects.requireNonNull(clientContext);
        this.clientContext = clientContext;
    }

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()

                .antMatchers("/", "/login**", "/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll()

                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                .and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter ssoFilter = new OAuth2ClientAuthenticationProcessingFilter(
                "/login");
        OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(oauth2Client, clientContext);
        ssoFilter.setRestTemplate(facebookTemplate);
        ssoFilter.setTokenServices(resourceServerTokenServices);
        ssoFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return ssoFilter;
    }
}

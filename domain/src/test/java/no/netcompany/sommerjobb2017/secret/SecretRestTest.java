package no.netcompany.sommerjobb2017.secret;

import no.netcompany.sommerjobb2017.user.User;
import no.netcompany.sommerjobb2017.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Knut Esten Melandsø Nekså
 */
@RunWith(MockitoJUnitRunner.class)
public class SecretRestTest {
    @Mock
    private SecretService secretService;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @Mock
    private Secret expectedSecret;

    @InjectMocks
    private SecretRest secretRest;

    @Test
    public void getSecret() throws Exception {
        when(userService.getSignedInUser()).thenReturn(user);
        when(secretService.getSecretForUser(user)).thenReturn(expectedSecret);

        final Secret secret = secretRest.getSecret();

        assertThat(secret).isEqualTo(expectedSecret);
    }
}
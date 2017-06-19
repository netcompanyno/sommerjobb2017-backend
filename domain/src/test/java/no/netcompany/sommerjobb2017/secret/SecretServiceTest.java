package no.netcompany.sommerjobb2017.secret;

import no.netcompany.sommerjobb2017.user.User;
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
public class SecretServiceTest {
    @Mock
    private SecretDao secretDao;
    @Mock
    private User user;
    @Mock
    private Secret expectedSecret;

    @InjectMocks
    private SecretService secretService;

    @Test
    public void getSecretForUser_shouldCallDaoAndReturnSecret() {
        when(secretDao.get(1337)).thenReturn(expectedSecret);
        when(user.getId()).thenReturn(1337);

        final Secret secret = secretService.getSecretForUser(user);

        assertThat(secret).isEqualTo(expectedSecret);
    }
}
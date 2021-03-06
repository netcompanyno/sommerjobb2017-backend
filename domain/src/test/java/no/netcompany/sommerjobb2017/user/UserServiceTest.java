package no.netcompany.sommerjobb2017.user;

import no.netcompany.sommerjobb2017.secret.SecretService;
import no.netcompany.sommerjobb2017.util.SecurityContextHolderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Knut Esten Melandsø Nekså
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private SecretService secretService;
    @Mock
    private SecurityContextHolderFactory factory;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;
    @Mock
    private User expectedUser;
    @Mock
    private OAuth2User oAuth2User;

    @InjectMocks
    private UserService userService;

    @Test
    public void getSignedInUser_returnSignedInUser() {
        when(factory.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@test.no");
        when(userDao.getByEmail("test@test.no")).thenReturn(expectedUser);

        final User user = userService.getSignedInUser();

        assertThat(user).isEqualTo(expectedUser);
    }

    @Test
    public void getByEmail_shouldCallDao() {
        when(userDao.getByEmail("test@test.no")).thenReturn(expectedUser);

        final User user = userService.getByEmail("test@test.no");

        assertThat(user).isEqualTo(expectedUser);
    }

    @Test
    public void create_shouldCallUserDaoToCreateUserAndSecret() {
        when(userDao.create(oAuth2User)).thenReturn(1);

        userService.create(oAuth2User);

        verify(userDao).create(oAuth2User);
        verify(secretService).create(1);
    }
}
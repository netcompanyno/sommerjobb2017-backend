package no.netcompany.sommerjobb2017.user;

import no.netcompany.sommerjobb2017.secret.SecretService;
import no.netcompany.sommerjobb2017.util.SecurityContextHolderFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Service
public class UserService {
    private final UserDao userDao;
    private final SecretService secretService;
    private final SecurityContextHolderFactory securityContextHolderFactory;

    public UserService(final UserDao userDao,
                       final SecretService secretService,
                       final SecurityContextHolderFactory securityContextHolderFactory) {
        Objects.requireNonNull(secretService);
        this.secretService = secretService;
        Objects.requireNonNull(securityContextHolderFactory);
        this.securityContextHolderFactory = securityContextHolderFactory;
        Objects.requireNonNull(userDao);
        this.userDao = userDao;
    }

    public User getSignedInUser() {
        final Authentication auth = securityContextHolderFactory.getContext().getAuthentication();
        return userDao.getByEmail(auth.getName());
    }

    public User getByEmail(final String email) {
        return userDao.getByEmail(email);
    }

    public void create(final OAuth2User user) {
        final int userId = userDao.create(user);
        secretService.create(userId);
    }
}

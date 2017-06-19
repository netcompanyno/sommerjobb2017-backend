package no.netcompany.sommerjobb2017.user;

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
    private final SecurityContextHolderFactory securityContextHolderFactory;

    public UserService(final UserDao userDao, final SecurityContextHolderFactory securityContextHolderFactory) {
        Objects.requireNonNull(securityContextHolderFactory);
        this.securityContextHolderFactory = securityContextHolderFactory;
        Objects.requireNonNull(userDao);
        this.userDao = userDao;
    }

    public User getSignedInUser() {
        final Authentication auth = securityContextHolderFactory.getContext().getAuthentication();
        return userDao.getByEmail(auth.getName());
    }
}

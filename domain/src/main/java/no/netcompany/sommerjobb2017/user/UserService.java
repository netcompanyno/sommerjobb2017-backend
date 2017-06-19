package no.netcompany.sommerjobb2017.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Service
public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        Objects.requireNonNull(userDao);
        this.userDao = userDao;
    }

    public User getSignedInUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.getByEmail(auth.getName());
    }
}

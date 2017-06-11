package no.netcompany.sommerjobb2017.user;

import org.springframework.security.crypto.bcrypt.BCrypt;
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

    public User authenticateAndReturn(final Credentials credentials) {
        final String passwordHash = userDao.getPasswordHash(credentials.getEmail());
        if (BCrypt.checkpw(credentials.getPassword(), passwordHash)) {
            return userDao.getByEmail(credentials.getEmail());
        }

        return null;
    }

    public void changePassword(final User user, final String newPassword) {
        final String salt = BCrypt.gensalt();
        final String newPasswordHash = BCrypt.hashpw(newPassword, salt);

        userDao.changePassword(user.getId(), newPasswordHash);
    }
}

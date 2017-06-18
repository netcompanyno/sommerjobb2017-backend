package no.netcompany.sommerjobb2017.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Service
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserDao userDao, final PasswordEncoder passwordEncoder) {
        Objects.requireNonNull(passwordEncoder);
        this.passwordEncoder = passwordEncoder;
        Objects.requireNonNull(userDao);
        this.userDao = userDao;
    }

    public User authenticateAndReturn(final Credentials credentials) {
        final String passwordHash = userDao.getPasswordHash(credentials.getEmail());
        if (passwordEncoder.matches(credentials.getPassword(), passwordHash)) {
            return userDao.getByEmail(credentials.getEmail());
        }

        return null;
    }

    public void changePassword(final User user, final String newPassword) {
        final String newPasswordHash = passwordEncoder.encode(newPassword);
        userDao.changePassword(user.getEmail(), newPasswordHash);
    }
}

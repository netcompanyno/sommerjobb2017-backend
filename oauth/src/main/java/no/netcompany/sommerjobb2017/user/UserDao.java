package no.netcompany.sommerjobb2017.user;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate namedTemplate;

    public UserDao(final NamedParameterJdbcTemplate namedTemplate) {
        Objects.requireNonNull(namedTemplate);
        this.namedTemplate = namedTemplate;
    }

    public String getPasswordHash(final String email) {
        return namedTemplate.queryForObject(
                "SELECT password_hash FROM \"user\" WHERE email = :email",
                new MapSqlParameterSource().addValue("email", email),
                String.class);
    }

    public User getByEmail(final String email) {
        return DataAccessUtils.requiredSingleResult(
                namedTemplate.query(
                        "SELECT * FROM \"user\" WHERE email = :email",
                        new MapSqlParameterSource().addValue("email", email),
                        (rs, i) -> ImmutableUser.builder()
                                .id(rs.getInt("id"))
                                .email(rs.getString("email"))
                                .name(rs.getString("name"))
                                .build()));
    }

    public void changePassword(final String email, final String newPasswordHash) {
        namedTemplate.update(
                "UPDATE \"user\" SET password_hash = :password_hash WHERE email = :email",
                new MapSqlParameterSource()
                        .addValue("password_hash", newPasswordHash)
                        .addValue("email", email));
    }
}

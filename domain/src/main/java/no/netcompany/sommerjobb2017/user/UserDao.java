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

    public User getByEmail(final String email) {
        return DataAccessUtils.requiredSingleResult(
                namedTemplate.query(
                        "SELECT * FROM users WHERE email = :email",
                        new MapSqlParameterSource().addValue("email", email),
                        (rs, i) -> ImmutableUser.builder()
                                .id(rs.getInt("id"))
                                .email(rs.getString("email"))
                                .name(rs.getString("name"))
                                .build()));
    }
}

package no.netcompany.sommerjobb2017.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import no.netcompany.sommerjobb2017.secret.ImmutableSecret;
import org.immutables.value.Value;

/**
 * @author Knut Esten Melandsø Nekså
 */

@JsonDeserialize(as = ImmutableCredentials.class)
@Value.Immutable
public interface Credentials {
    String getEmail();

    String getPassword();
}

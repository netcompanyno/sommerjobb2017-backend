package no.netcompany.sommerjobb2017.secret;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

/**
 * @author Knut Esten Melandsø Nekså
 */
@JsonDeserialize(as = ImmutableSecret.class)
@Value.Immutable
public interface Secret {
    @Value.Default
    default String getSecret() {
        return "";
    }
}

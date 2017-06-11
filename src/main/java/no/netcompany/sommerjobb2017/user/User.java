package no.netcompany.sommerjobb2017.user;

import org.immutables.value.Value;

/**
 * @author Knut Esten Melandsø Nekså
 */
@Value.Immutable
public interface User {
    int getId();

    String getEmail();

    String getName();
}

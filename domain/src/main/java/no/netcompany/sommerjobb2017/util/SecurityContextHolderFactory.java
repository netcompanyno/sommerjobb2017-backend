package no.netcompany.sommerjobb2017.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Factory to simplify testing of SecurityContextHolder.
 *
 * @author Knut Esten Melandsø Nekså
 */
@Service
public class SecurityContextHolderFactory {

    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }
}

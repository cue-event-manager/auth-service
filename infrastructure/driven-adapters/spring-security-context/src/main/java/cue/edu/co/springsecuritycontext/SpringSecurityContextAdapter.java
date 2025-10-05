package cue.edu.co.springsecuritycontext;

import cue.edu.co.model.auth.gateways.AuthContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityContextAdapter implements AuthContext {
    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new IllegalStateException("No authenticated user found in SecurityContext");
        }

        try {
            return Long.parseLong(authentication.getPrincipal().toString());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Invalid user ID format in authentication principal", e);
        }
    }
}

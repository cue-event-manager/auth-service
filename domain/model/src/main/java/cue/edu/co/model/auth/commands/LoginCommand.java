package cue.edu.co.model.auth.commands;

import java.util.Optional;

public record LoginCommand(
        String email,
        String password,
        String ipAddress,
        String userAgent,
        Optional<Boolean> acceptTerms

) {

    public boolean hasAcceptedTerms(){
        return acceptTerms.isPresent() && acceptTerms.get();
    }
}

package cue.edu.co.model.auth.gateways;

import cue.edu.co.model.user.User;

public interface TokenProvider {
    String generate(User user);
}

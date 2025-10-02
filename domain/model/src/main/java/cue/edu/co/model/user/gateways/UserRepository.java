package cue.edu.co.model.user.gateways;

import cue.edu.co.model.user.User;


public interface UserRepository {
    User save(User user);
    boolean existsByIdentification(String identification);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
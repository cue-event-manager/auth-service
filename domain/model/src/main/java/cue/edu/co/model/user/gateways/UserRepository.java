package cue.edu.co.model.user.gateways;

import cue.edu.co.model.user.User;

import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    boolean existsByIdentification(String identification);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
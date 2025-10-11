package cue.edu.co.model.user.gateways;

import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.queries.UserPaginationQuery;

import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByIdentification(String identification);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long id);
    PageResult<User> findAllByFilters(UserPaginationQuery userPaginationQuery);
    boolean existsByIdentification(String identification);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
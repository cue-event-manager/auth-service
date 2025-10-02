package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByIdentification(String identification);
    boolean existsByPhoneNumber(String phoneNumber);
}

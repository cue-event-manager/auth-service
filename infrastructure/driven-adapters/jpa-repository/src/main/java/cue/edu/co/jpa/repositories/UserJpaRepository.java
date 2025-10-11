package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByIdentification(String identification);

    boolean existsByEmail(String email);
    boolean existsByIdentification(String identification);
    boolean existsByPhoneNumber(String phoneNumber);
}

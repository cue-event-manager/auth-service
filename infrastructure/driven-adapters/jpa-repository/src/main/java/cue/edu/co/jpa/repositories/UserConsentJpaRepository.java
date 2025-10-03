package cue.edu.co.jpa.repositories;


import cue.edu.co.jpa.entities.UserConsentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserConsentJpaRepository extends CrudRepository<UserConsentEntity, Long> {
    Optional<UserConsentEntity> findTopByUserIdOrderByAcceptedAtDesc(Long userId);
}

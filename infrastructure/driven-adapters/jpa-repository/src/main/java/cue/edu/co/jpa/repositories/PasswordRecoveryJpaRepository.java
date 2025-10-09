package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.PasswordRecoveryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordRecoveryJpaRepository extends CrudRepository<PasswordRecoveryEntity, Long> {
    Optional<PasswordRecoveryEntity> findByEmail(String email);

    Optional<PasswordRecoveryEntity> findByEmailAndCode(String email, String code);

    @Modifying
    @Query("""
        UPDATE PasswordRecoveryEntity pr
        SET pr.used = true
        WHERE pr.email = :email AND pr.used = false
    """)
    void invalidateAllByEmail(@Param("email") String email);
}

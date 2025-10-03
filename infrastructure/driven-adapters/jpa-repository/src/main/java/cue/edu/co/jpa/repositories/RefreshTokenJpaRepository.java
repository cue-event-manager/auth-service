package cue.edu.co.jpa.repositories;

import cue.edu.co.jpa.entities.RefreshTokenEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends CrudRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);

    @Modifying
    @Query("update RefreshTokenEntity r set r.revoked = true where r.id = :id")
    void revokeById(Long id);

    @Modifying
    @Query("update RefreshTokenEntity r set r.revoked = true where r.userId = :userId")
    void revokeAllByUserId(Long userId);

}

package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.Invite;

import java.util.Optional;
@Repository
public interface InviteRepository extends CrudRepository<Invite, Long> {
    Optional<Invite> findByCode(String code);
    boolean existsByCode(String code);
}

package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.StudentClass;
import ru.kpfu.models.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
    List<User> findByStudentClass(StudentClass studentClass);
    boolean existsByLogin(String login);
}

package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.StudentClass;
import ru.kpfu.models.User;

import java.util.Optional;

@Repository
public interface StudentClassRepository extends CrudRepository<StudentClass, Long> {
}

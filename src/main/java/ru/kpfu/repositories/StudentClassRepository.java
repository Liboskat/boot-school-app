package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.StudentClass;

@Repository
public interface StudentClassRepository extends CrudRepository<StudentClass, Long> {
}

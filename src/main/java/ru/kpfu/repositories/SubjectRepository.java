package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.Subject;
@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}

package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.Lesson;
import ru.kpfu.models.Mark;
import ru.kpfu.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
    Optional<Mark> findByLessonAndDateAndStudent(Lesson lesson, Date date, User student);
    List<Mark> findByLessonAndStudent(Lesson lesson, User student);
}

package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.Homework;
import ru.kpfu.models.Lesson;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HomeworkRepository extends CrudRepository<Homework, Long> {
    Optional<Homework> findByLessonAndDate(Lesson lesson, Date date);
    List<Homework> findByLesson(Lesson lesson);
}

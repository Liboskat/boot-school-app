package ru.kpfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.Lesson;
import ru.kpfu.models.StudentClass;
import ru.kpfu.models.User;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository <Lesson, Long> {
    List<Lesson> getByTeacher(User user);
    List<Lesson> getByStudentClass(StudentClass studentClass);
    List<Lesson> getByWeekdayAndTeacher(Integer weekday, User teacher);
}

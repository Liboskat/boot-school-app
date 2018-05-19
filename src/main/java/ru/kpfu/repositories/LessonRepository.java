package ru.kpfu.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.models.Lesson;
import ru.kpfu.models.StudentClass;
import ru.kpfu.models.User;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository <Lesson, Long> {
    @Cacheable(value = "lessons")
    List<Lesson> getByTeacher(User user);
    @Cacheable(value = "lessons")
    List<Lesson> getByStudentClass(StudentClass studentClass);
    @Cacheable(value = "lessons")
    List<Lesson> getByWeekdayAndTeacher(Integer weekday, User teacher);
}

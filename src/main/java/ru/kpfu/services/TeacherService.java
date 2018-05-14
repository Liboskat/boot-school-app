package ru.kpfu.services;

import org.springframework.stereotype.Service;
import ru.kpfu.dtos.LessonDto;
import ru.kpfu.dtos.UserDto;
import ru.kpfu.forms.AddHomeworkForm;
import ru.kpfu.forms.AddMarkForm;
import ru.kpfu.models.Homework;

import java.util.Date;
import java.util.List;
@Service
public interface TeacherService {
    List<LessonDto> getTimetable(String login);
    List<LessonDto> getLessonsByDate(String login, Date date);
    Homework getHomeworkByLessonIdAndDate(String lessonId, Date date);
    List<UserDto> getStudentsByLesson(String lessonId);
    void saveMark(AddMarkForm form);
    void saveHomework(AddHomeworkForm form);
}

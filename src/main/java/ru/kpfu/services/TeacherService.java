package ru.kpfu.services;

import org.springframework.stereotype.Service;
import ru.kpfu.dtos.LessonDto;
import ru.kpfu.dtos.MarkDto;
import ru.kpfu.dtos.UserDto;
import ru.kpfu.forms.AddHomeworkForm;
import ru.kpfu.forms.AddMarkForm;
import ru.kpfu.models.Homework;

import java.security.Principal;
import java.util.List;
@Service
public interface TeacherService {
    List<LessonDto> getTimetable(Principal principal);
    List<LessonDto> getLessonsByDate(Principal principal, String date);
    Homework getHomeworkByLessonAndDate(String lessonId, String date);
    List<UserDto> getStudentsByLesson(String lessonId);
    void saveMark(AddMarkForm form);
    void saveHomework(AddHomeworkForm form);
    List<MarkDto> getMarksByLessonAndDate(String lessonId, String date);
}

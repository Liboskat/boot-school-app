package ru.kpfu.services;

import org.springframework.stereotype.Service;
import ru.kpfu.dtos.DiaryUnit;
import ru.kpfu.dtos.LessonDto;

import java.util.List;
@Service
public interface StudentService {
    List<LessonDto> getTimetable(String login);
    List<DiaryUnit> getDiary(String login, int week);
}

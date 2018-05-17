package ru.kpfu.services;

import org.springframework.stereotype.Service;
import ru.kpfu.dtos.DiaryUnit;
import ru.kpfu.dtos.LessonDto;

import java.security.Principal;
import java.util.List;
@Service
public interface StudentService {
    List<LessonDto> getTimetable(Principal principal);
    List<DiaryUnit> getDiary(Principal principal, int week);
}

package ru.kpfu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.kpfu.dtos.DiaryUnit;
import ru.kpfu.dtos.LessonDto;
import ru.kpfu.models.*;
import ru.kpfu.repositories.HomeworkRepository;
import ru.kpfu.repositories.LessonRepository;
import ru.kpfu.repositories.MarkRepository;
import ru.kpfu.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final HomeworkRepository homeworkRepository;
    private final MarkRepository markRepository;
    private final DateUtil dateUtil;

    @Autowired
    public StudentServiceImpl(UserRepository userRepository, LessonRepository lessonRepository, HomeworkRepository homeworkRepository, MarkRepository markRepository, DateUtil dateUtil) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.homeworkRepository = homeworkRepository;
        this.markRepository = markRepository;
        this.dateUtil = dateUtil;
    }

    @Override
    public List<LessonDto> getTimetable(String login) {
        User student = userRepository.findByLogin(login).orElseThrow(() -> new DataAccessException("Ошибка базы данных") {
        });
        List<LessonDto> list = new ArrayList<>();
        List<Lesson> lessons = lessonRepository.getByStudentClass(student.getStudentClass().get(0));
        Collections.sort(lessons);
        lessons.forEach(l -> list.add(LessonDto.buildFrom(l)));
        return list;
    }

    @Override
    public List<DiaryUnit> getDiary(String login, int week) {
        List<DiaryUnit> diary = new ArrayList<>();
        User student = userRepository.findByLogin(login).orElseThrow(() -> new DataAccessException("Ошибка базы данных") {});
        List<Lesson> lessons = lessonRepository.getByStudentClass(student.getStudentClass().get(0));
        Collections.sort(lessons);
        for (Lesson lesson : lessons) {
            List<Homework> allHomework = homeworkRepository.findByLesson(lesson);
            List<Homework> filteredHomework = filterHomeworkByWeek(allHomework, week);
            Homework homework = !filteredHomework.isEmpty() ? filteredHomework.get(0) : null;

            List<Mark> allMarks = markRepository.findByLessonAndStudent(lesson, student);
            List<Mark> filteredMarks = filterMarkByWeek(allMarks, week);
            Mark mark = !filteredMarks.isEmpty() ? filteredMarks.get(0) : null;

            diary.add(DiaryUnit.buildFrom(lesson, homework, mark, null));
        }
        return diary;
    }

    private List<Homework> filterHomeworkByWeek(List<Homework> homework, int week) {
        return homework.stream().filter(h -> checkIfInWeek(h, week)).collect(Collectors.toList());
    }

    private List<Mark> filterMarkByWeek(List<Mark> marks, int week) {
        return marks.stream().filter(m -> checkIfInWeek(m, week)).collect(Collectors.toList());
    }

    private boolean checkIfInWeek(Homework homework, int week) {
        return dateUtil.toWeek(homework.getDate()) == week;
    }

    private boolean checkIfInWeek(Mark mark, int week) {
        return dateUtil.toWeek(mark.getDate()) == week;
    }
}

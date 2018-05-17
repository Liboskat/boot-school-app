package ru.kpfu.dtos;

import lombok.*;
import ru.kpfu.models.Lesson;
import ru.kpfu.services.DateHelper;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LessonDto implements Serializable {
    private String subject;
    private String studentClass;
    private String teacher;
    private String lessonNumber;
    private String startTime;
    private String endTime;
    private String weekday;
    private String room;
    private String id;

    public static LessonDto buildFrom(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId() + "")
                .studentClass(StudentClassDto.buildFrom(lesson.getStudentClass()).toString())
                .subject(lesson.getSubject().getName())
                .teacher(lesson.getTeacher().getSurname() + " " + lesson.getTeacher().getSurname()
                        + " " + lesson.getTeacher().getPatronymic())
                .lessonNumber(lesson.getLessonNumberTime().getLessonNumber() + "")
                .startTime(lesson.getLessonNumberTime().getStartTime())
                .endTime(lesson.getLessonNumberTime().getEndTime())
                .weekday(DateHelper.getNameOfWeekday(lesson.getWeekday()))
                .room(lesson.getRoom())
                .build();
    }

}

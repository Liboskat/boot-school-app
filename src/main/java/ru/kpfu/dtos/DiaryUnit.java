package ru.kpfu.dtos;

import lombok.*;
import ru.kpfu.models.Homework;
import ru.kpfu.models.Lesson;
import ru.kpfu.models.Mark;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DiaryUnit implements Serializable {
    private LessonDto lessonDto;
    private String date;
    private String homework;
    private String mark;

    public static DiaryUnit buildFrom(Lesson lesson, Homework homework, Mark mark, Date date) {
        String dateStr;
        if(date != null) {
            DateFormat dateFormat = new SimpleDateFormat();
            dateStr = dateFormat.format(date);
        } else {
            dateStr = "";
        }

        String homeworkStr = homework != null ? homework.getText() : null;
        String markStr = mark != null ? mark.getValue() : null;
        return DiaryUnit.builder()
                .lessonDto(LessonDto.buildFrom(lesson))
                .homework(homeworkStr)
                .mark(markStr)
                .date(dateStr)
                .build();
    }
}

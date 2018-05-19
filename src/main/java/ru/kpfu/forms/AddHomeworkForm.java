package ru.kpfu.forms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddHomeworkForm {
    private String lessonId;
    private String date;
    private String homeworkText;
}

package ru.kpfu.forms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddMarkForm {
    private String lessonId;
    private String date;
    private String studentId;
    private String value;
}
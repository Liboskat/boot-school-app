package ru.kpfu.forms;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddHomeworkForm {
    @NotNull
    private String lessonId;
    @NotNull
    private String date;
    @NotNull
    private String homeworkText;
}

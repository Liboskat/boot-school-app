package ru.kpfu.forms;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddMarkForm {
    @NotNull
    private String lessonId;
    @NotNull
    private String date;
    @NotNull
    private String studentId;
    @NotNull
    private String value;
}
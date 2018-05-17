package ru.kpfu.dtos;

import lombok.*;
import ru.kpfu.models.Mark;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MarkDto implements Serializable {
    private String id;
    private String value;
    private String date;
    private String student;

    public static MarkDto buildFrom(Mark mark) {
        String dateStr;
        if(mark.getDate() != null) {
            DateFormat dateFormat = new SimpleDateFormat();
            dateStr = dateFormat.format(mark.getDate());
        } else {
            dateStr = "";
        }
        return MarkDto.builder()
                .id(mark.getId().toString())
                .value(mark.getValue())
                .date(dateStr)
                .student(mark.getStudent().getName() + " " + mark.getStudent().getSurname())
                .build();
    }
}

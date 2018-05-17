package ru.kpfu.dtos;

import lombok.*;
import ru.kpfu.models.StudentClass;
import ru.kpfu.services.DateHelper;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentClassDto implements Serializable {
    private String id;
    private String number;
    private String letter;

    public static StudentClassDto buildFrom(StudentClass studentClass) {
        return StudentClassDto.builder()
                .id(studentClass.getId() + "")
                .number(DateHelper.getDifferenceBetweenCurrentYearAndAnother(studentClass.getFirstYear()) + 1 + "")
                .letter(studentClass.getLetter())
                .build();
    }

    @Override
    public String toString() {
        return number + letter;
    }
}

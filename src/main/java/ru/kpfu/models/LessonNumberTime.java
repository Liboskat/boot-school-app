package ru.kpfu.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lesson_number_time")
public class LessonNumberTime {
    @Id
    @Column(name = "lesson_number")
    private Integer lessonNumber;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
}

package ru.kpfu.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lesson")
public class Lesson implements Comparable<Lesson>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private StudentClass studentClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    private Integer weekday;
    private String room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "number")
    private LessonNumberTime lessonNumberTime;

    @Override
    public String toString() {
        return subject.getName() + ", " + studentClass.toString() + ", " +
                teacher.getName() + " " + teacher.getSurname() + ", " + weekday + ", " +
                lessonNumberTime.getLessonNumber() + ", " + lessonNumberTime.getStartTime();
    }

    @Override
    public int compareTo(Lesson lesson) {
        int diff = weekday.compareTo(lesson.getWeekday()) * 10;
        if(diff == 0 && lessonNumberTime != null && lesson.getLessonNumberTime() != null) {
            diff = lessonNumberTime.getLessonNumber().compareTo(lesson.getLessonNumberTime().getLessonNumber());
        }
        return diff;
    }
}

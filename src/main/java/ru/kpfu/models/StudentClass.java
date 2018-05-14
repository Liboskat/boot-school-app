package ru.kpfu.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "class")
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_year")
    private Integer firstYear;

    private String letter;

    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE})
    @JoinTable(
            name = "student_info",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> students;

    @Override
    public String toString() {
        return firstYear + " " + letter;
    }
}

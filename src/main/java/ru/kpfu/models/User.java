package ru.kpfu.models;

import lombok.*;
import ru.kpfu.security.roles.UserRole;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;

    //only for students
    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE})
    @JoinTable(
            name = "student_info",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<StudentClass> studentClass;
}

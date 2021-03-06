package ru.kpfu.models;

import lombok.*;
import ru.kpfu.security.roles.UserRole;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "invite")
public class Invite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String code;
    @Column(name = "is_used")
    private Boolean isUsed;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;
}

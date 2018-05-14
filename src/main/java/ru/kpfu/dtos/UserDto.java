package ru.kpfu.dtos;

import lombok.*;
import ru.kpfu.models.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String name;
    private String surname;

    public static UserDto buildFrom(User user) {
        return UserDto.builder()
                .id(user.getId() + "")
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}

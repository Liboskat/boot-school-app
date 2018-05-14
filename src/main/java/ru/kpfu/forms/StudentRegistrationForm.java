package ru.kpfu.forms;

import lombok.*;
import ru.kpfu.validators.FieldsValueMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "passwordRepeat",
                message = "Passwords do not match!"
        )
})
public class StudentRegistrationForm {
    @NotNull
    private String invite;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String passwordRepeat;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String patronymic;
    private String email;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "not correct phone number")
    private String phoneNumber;
    @NotNull
    private String classId;
}

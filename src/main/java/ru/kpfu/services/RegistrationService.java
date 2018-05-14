package ru.kpfu.services;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import ru.kpfu.dtos.StudentClassDto;
import ru.kpfu.exceptions.InviteNotFoundException;
import ru.kpfu.forms.StudentRegistrationForm;
import ru.kpfu.forms.TeacherRegistrationForm;

import java.util.List;

@Service
public interface RegistrationService {
    void registerStudent(StudentRegistrationForm form) throws DuplicateKeyException, InviteNotFoundException;
    void registerTeacher(TeacherRegistrationForm form) throws DuplicateKeyException, InviteNotFoundException;
    List<StudentClassDto> getClasses();
}

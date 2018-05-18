package ru.kpfu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.dtos.StudentClassDto;
import ru.kpfu.exceptions.InviteNotFoundException;
import ru.kpfu.forms.StudentRegistrationForm;
import ru.kpfu.forms.TeacherRegistrationForm;
import ru.kpfu.models.Invite;
import ru.kpfu.models.StudentClass;
import ru.kpfu.models.User;
import ru.kpfu.repositories.InviteRepository;
import ru.kpfu.repositories.StudentClassRepository;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.security.roles.UserRole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private UserRepository userRepository;
    private InviteRepository inviteRepository;
    private StudentClassRepository studentClassRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, InviteRepository inviteRepository, StudentClassRepository studentClassRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.inviteRepository = inviteRepository;
        this.studentClassRepository = studentClassRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerStudent(StudentRegistrationForm form)  throws DuplicateKeyException, InviteNotFoundException {
        Optional<Invite> inviteOptional = inviteRepository.findByCode(form.getInvite());
        Invite invite = inviteOptional.orElseThrow(() -> new InviteNotFoundException("Неверный пригласительный код"));
        if(invite.getIsUsed()) {
            throw new DuplicateKeyException("Пригласительный код уже использован");
        }
        if(userRepository.existsByLogin(form.getLogin())) {
            throw new DuplicateKeyException("Логин уже использован");
        }
        StudentClass studentClass = studentClassRepository.findOne(Long.parseLong(form.getClassId()));
        User user = User.builder()
                .userRole(UserRole.STUDENT)
                .name(form.getName())
                .surname(form.getSurname())
                .patronymic(form.getPatronymic())
                .login(form.getLogin())
                .password(passwordEncoder.encode(form.getPassword()))
                .phoneNumber(form.getPhoneNumber())
                .email(form.getEmail())
                .studentClass(Collections.singletonList(studentClass))
                .build();
        userRepository.save(user);
        studentClass.getStudents().add(user);
        invite.setIsUsed(true);
        inviteRepository.save(invite);
    }

    @Override
    public void registerTeacher(TeacherRegistrationForm form) throws DuplicateKeyException, InviteNotFoundException {
        Optional<Invite> inviteOptional = inviteRepository.findByCode(form.getInvite());
        Invite invite = inviteOptional.orElseThrow(() -> new InviteNotFoundException("Неверный пригласительный код"));
        if(invite.getIsUsed()) {
            throw new DuplicateKeyException("Пригласительный код уже использован");
        }
        if(userRepository.existsByLogin(form.getLogin())) {
            throw new DuplicateKeyException("Логин уже использован");
        }

        User user = User.builder()
                .userRole(UserRole.TEACHER)
                .name(form.getName())
                .surname(form.getSurname())
                .patronymic(form.getPatronymic())
                .login(form.getLogin())
                .password(passwordEncoder.encode(form.getPassword()))
                .phoneNumber(form.getPhoneNumber())
                .email(form.getEmail())
                .build();
        userRepository.save(user);
        invite.setIsUsed(true);
        inviteRepository.save(invite);
    }

    @Override
    @Cacheable("studentClasses")
    public List<StudentClassDto> getClasses() {
        List<StudentClassDto> studentClassDtos = new ArrayList<>();
        studentClassRepository.findAll().forEach(c -> studentClassDtos.add(StudentClassDto.buildFrom(c)));
        return studentClassDtos;
    }
}

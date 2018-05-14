package ru.kpfu.services;

import org.springframework.stereotype.Service;
import ru.kpfu.security.roles.UserRole;

@Service
public interface InviteService {
    String generate(UserRole role);
}

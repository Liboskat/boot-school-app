package ru.kpfu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.models.Invite;
import ru.kpfu.repositories.InviteRepository;
import ru.kpfu.security.roles.UserRole;

@Service
public class InviteServiceImpl implements InviteService {
    private final RandomInviteGenerator inviteGenerator;
    private final InviteRepository inviteRepository;

    @Autowired
    public InviteServiceImpl(RandomInviteGenerator inviteGenerator, InviteRepository inviteRepository) {
        this.inviteGenerator = inviteGenerator;
        this.inviteRepository = inviteRepository;
    }

    @Override
    public String generate(UserRole role) {
        String inviteCode = inviteGenerator.generate();
        while(inviteRepository.existsByCode(inviteCode)) {
            inviteCode = inviteGenerator.generate();
        }
        Invite invite = Invite.builder()
                .code(inviteCode)
                .userRole(role)
                .isUsed(false)
                .build();
        inviteRepository.save(invite);
        return invite.getCode();
    }
}

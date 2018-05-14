package ru.kpfu.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.models.User;
import ru.kpfu.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthenticationProviderImpl(UserRepository userRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<User> userOptional = userRepository.findByLogin(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Неверный логин или пароль");
            }
        } else {
            throw new BadCredentialsException("Неверный логин или пароль");
        }
        UserDetails details = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
        return new UsernamePasswordAuthenticationToken(details, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}

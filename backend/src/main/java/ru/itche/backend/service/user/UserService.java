package ru.itche.backend.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.config.JwtUserDetails;
import ru.itche.backend.controller.user.payload.UpdateUserPayload;
import ru.itche.backend.entity.auth.Role;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(String login, String password, String email, String phone, Role role) {

        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new JwtUserDetails(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().toUpperCase()))
        );
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + login));
    }

    @Transactional
    public void updateUser(Long id, UpdateUserPayload payload) {
        userRepository.findById(id)
                .ifPresent(user -> {

                    Optional.ofNullable(payload.login())
                            .ifPresent(user::setLogin);

                    Optional.ofNullable(payload.password()) //переднлать логику: доб. проверку старюю пароля
                            .ifPresent(user::setPassword);

                    Optional.ofNullable(payload.email())
                            .ifPresent(user::setEmail);

                    Optional.ofNullable(payload.phone())
                            .ifPresent(user::setPhone);
                });
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

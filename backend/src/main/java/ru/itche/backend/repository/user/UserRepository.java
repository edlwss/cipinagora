package ru.itche.backend.repository.user;

import org.springframework.data.repository.CrudRepository;
import ru.itche.backend.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLogin(String username);
}

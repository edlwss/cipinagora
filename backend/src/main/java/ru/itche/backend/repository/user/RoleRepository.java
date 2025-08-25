package ru.itche.backend.repository.user;

import org.springframework.data.repository.CrudRepository;
import ru.itche.backend.entity.auth.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String student);
}

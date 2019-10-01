package ru.drsdgdby.springdatatest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.drsdgdby.springdatatest.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}

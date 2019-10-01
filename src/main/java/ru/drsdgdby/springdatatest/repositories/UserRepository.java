package ru.drsdgdby.springdatatest.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.drsdgdby.springdatatest.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}

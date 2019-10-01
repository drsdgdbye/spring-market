package ru.drsdgdby.springdatatest.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.drsdgdby.springdatatest.entities.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String name);
}

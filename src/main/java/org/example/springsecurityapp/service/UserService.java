package org.example.springsecurityapp.service;

import org.example.springsecurityapp.model.User;

/**
 * Сервис по работе с пользователями.
 *
 * @author r.kaminin
 */
public interface UserService {

    void save(User user);

    User findByUserName(String userName);
}
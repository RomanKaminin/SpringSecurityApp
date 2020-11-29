package org.example.springsecurityapp.dao;

import org.example.springsecurityapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
package org.example.springsecurityapp.service;

import org.example.springsecurityapp.dao.RoleDao;
import org.example.springsecurityapp.dao.UserDao;
import org.example.springsecurityapp.model.Role;
import org.example.springsecurityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация {@link UserService}.
 *
 * @author r.kaminin
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired()
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        // устанавливаем по умолчанию роль 'user'
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);

    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
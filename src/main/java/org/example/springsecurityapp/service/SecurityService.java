package org.example.springsecurityapp.service;

public interface SecurityService {

    String findLoggedInUserName();

    void autoLogin(String userName, String password);
}
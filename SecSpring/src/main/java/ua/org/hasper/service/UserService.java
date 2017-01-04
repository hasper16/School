package ua.org.hasper.service;

import ua.org.hasper.Entity.CustomUser;

public interface UserService {
    CustomUser getUserByLogin(String login);
    void addUser(CustomUser customUser);
}

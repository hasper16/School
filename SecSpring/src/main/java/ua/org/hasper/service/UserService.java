package ua.org.hasper.service;

import ua.org.hasper.Entity.CustomUser;
import ua.org.hasper.Entity.Enums.UserRole;

import java.util.List;

public interface UserService {
    CustomUser getUserByLogin(String login);
    CustomUser getUserById(int id);
    void addUser(CustomUser customUser);
    List<CustomUser> getUsersByRole(UserRole role);
    void deleteUser(CustomUser customUser);
}

package ua.org.hasper.service;

import org.springframework.data.domain.Page;
import ua.org.hasper.Entity.CustomUser;
import ua.org.hasper.Entity.Enums.UserRole;

import java.util.List;

public interface UserService {
    CustomUser getUserByLogin(String login);

    CustomUser getUserById(int id);

    void addUser(CustomUser customUser);

    List<CustomUser> getUsersByRole(UserRole role);

    Page<CustomUser> getUsersByRole(UserRole role, int page, int pageSize);

    void deleteUser(CustomUser customUser);
}

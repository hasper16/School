package ua.org.hasper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.hasper.Entity.CustomUser;
import ua.org.hasper.Entity.Enums.UserRole;
import ua.org.hasper.repository.UserRepository;
import ua.org.hasper.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomUser getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void addUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Override
    @Transactional
    public List<CustomUser> getUsersByRole(UserRole role) {
        return userRepository.findUsersbyRole(role);
    }

    @Override
    @Transactional
    public Page<CustomUser> getUsersByRole(UserRole role, int page,int pageSize) {
        return userRepository.findUsersbyRole(role, new PageRequest(page,pageSize));
    }

    @Override
    @Transactional
    public  void deleteUser(CustomUser customUser){
        userRepository.delete(customUser);
    }

    @Override
    @Transactional
    public  CustomUser getUserById(int id){
        return userRepository.findById(id);
    }
}

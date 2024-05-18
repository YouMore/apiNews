package com.example.apiNews.service;

import com.example.apiNews.dto.request.UserDTO;
import com.example.apiNews.model.entity.News;
import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.entity.enums.UserRole;
import com.example.apiNews.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

//    private static final String EXISTING_LOGIN = "youmore";

//    public Optional<Users> findByLogin(String login){
//        if (EXISTING_LOGIN.equalsIgnoreCase(login)) return Optional.empty();
//
//        var user = new Users();
//        user.setId(1L);
//        user.setLogin(EXISTING_LOGIN);
//        user.setPassword("$2a$12$0Zy1kQnAGIINFS78ZlQQbuRlHs/0feyjvzuDOQFXbKKz/45YEJpPS");
//        user.setRole(UserRole.ROLE_GUEST);
//        user.setNews(new ArrayList<>());
//        return Optional.of(user);
//    }
//
    private final UserRepository userRepository;
//
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    public Users create(Users user) {
//        System.out.println("Создан пользователь");
//        return userRepository.save(user);
//    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Users> getUsersByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }

    public Users getUserById(long id) {
        return userRepository.getById(id);
    }

    public Users getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Transactional
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    public Users updateUserById(Long id, UserDTO userDTO) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setLogin(userDTO.getLogin());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());

        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }
}

package com.example.apiNews.service;

import com.example.apiNews.dto.request.UserDTO;
import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.entity.enums.UserRole;
import com.example.apiNews.repository.NewsRepository;
import com.example.apiNews.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
//

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
        return userRepository.findById(id).orElse(null);
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
        // Удаление связанных новостей
        newsRepository.deleteByUserId(userId);

        // Удаление пользователя
        userRepository.deleteById(userId);
    }
}

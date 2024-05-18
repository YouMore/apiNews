package com.example.apiNews.service;

import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.entity.enums.UserRole;
import com.example.apiNews.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public EncryptionService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ROLE_GUEST);
        userRepository.save(user);
    }
}

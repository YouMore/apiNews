package com.example.apiNews.repository;

import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findAllByRole(UserRole role);
    Optional<Users> findByLogin(String login);
}

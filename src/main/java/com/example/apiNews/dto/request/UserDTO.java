package com.example.apiNews.dto.request;

import com.example.apiNews.model.entity.News;
import com.example.apiNews.model.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String login;
    private String password;
    private UserRole role;
    private List<News> news;
}

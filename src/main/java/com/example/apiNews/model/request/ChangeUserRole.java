package com.example.apiNews.model.request;

import com.example.apiNews.model.entity.enums.UserRole;
import lombok.Data;

@Data
public class ChangeUserRole {
    UserRole role;
}

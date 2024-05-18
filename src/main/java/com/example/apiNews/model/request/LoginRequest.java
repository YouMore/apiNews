package com.example.apiNews.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String login;

    private String password;
}

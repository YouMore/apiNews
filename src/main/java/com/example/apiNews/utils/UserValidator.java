package com.example.apiNews.utils;

import com.example.apiNews.model.entity.Users;
import com.example.apiNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users user = (Users) target;

        if (userService.getUserByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "",
                    "Пользователь с такой почтой уже зарегистрирован");
        }
    }
}

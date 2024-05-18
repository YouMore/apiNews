package com.example.apiNews.controller;
//

import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.response.LoginResponse;
import com.example.apiNews.model.request.LoginRequest;
import com.example.apiNews.service.AuthService;
import com.example.apiNews.service.EncryptionService;
import com.example.apiNews.utils.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final ModelMapper modelMapper;

    private final UserValidator userValidator;

    private final EncryptionService encryptionService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.attemptLogin(request.getLogin(), request.getPassword()));
    }

    @PostMapping("/registration")
    public ResponseEntity<Users> registration(@RequestBody @Valid LoginRequest request,
                                              BindingResult bindingResult){
        Users newUser = modelMapper.map(request, Users.class);
        System.out.println(newUser);
        userValidator.validate(newUser, bindingResult);
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        encryptionService.register(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

}
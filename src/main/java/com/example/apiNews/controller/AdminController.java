package com.example.apiNews.controller;

import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.request.ChangeUserRole;
import com.example.apiNews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Users>> adminPanel(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/edit/user/{id}")
    public ResponseEntity<Users> changeRole(@PathVariable long id, @RequestBody ChangeUserRole role){
        Users user = userService.getUserById(id);
        user.setRole(role.getRole());
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("delete/user/{id}")
    public ResponseEntity deleteUser(@PathVariable long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}

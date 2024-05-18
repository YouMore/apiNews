package com.example.apiNews.controller;

import com.example.apiNews.dto.request.UserDTO;
import com.example.apiNews.model.entity.Users;
import com.example.apiNews.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<Users> create(@RequestBody UserDTO userDTO){
//        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<Users>> readAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> read(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
//        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUserById(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        userService.deleteUserById(id);
        return HttpStatus.NO_CONTENT;
    }

}

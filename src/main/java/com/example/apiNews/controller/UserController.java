package com.example.apiNews.controller;

import com.example.apiNews.config.security.UserPrincipal;
import com.example.apiNews.model.entity.News;
import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.request.CreateNewsRequest;
import com.example.apiNews.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews(@AuthenticationPrincipal UserPrincipal principal){
        return ResponseEntity.ok(userService.getUserById(principal.getUserId()).getNews());
    }

    @PostMapping("/news/create")
    public ResponseEntity<News> createNews(@RequestBody CreateNewsRequest request,
                                           @AuthenticationPrincipal UserPrincipal principal){
        News news = modelMapper.map(request, News.class);
        news.setUser(userService.getUserById(principal.getUserId()));
        Users user = userService.getUserById(principal.getUserId());
        user.setNews(List.of(news));
        userService.updateUser(user);
        return ResponseEntity.created(news);
    }

}

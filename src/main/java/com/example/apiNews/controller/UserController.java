package com.example.apiNews.controller;

import com.example.apiNews.config.security.UserPrincipal;
import com.example.apiNews.model.entity.News;
import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.request.CreateNewsRequest;
import com.example.apiNews.service.NewsService;
import com.example.apiNews.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews(@AuthenticationPrincipal UserPrincipal principal){
        return ResponseEntity.ok(userService.getUserById(principal.getUserId()).getNews());
    }

    @GetMapping
    public ResponseEntity<List<Users>> readAll(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


//    @PostMapping("/news/create")
//    public ResponseEntity<News> createNews(@RequestBody CreateNewsRequest request,
//                                           @AuthenticationPrincipal UserPrincipal principal) {
//        News news = modelMapper.map(request, News.class);
//        Users user = userService.getUserById(principal.getUserId());
//        // Устанавливаем связь между новостью и пользователем
//        news.setUser(user);
//        user.getNews().add(news);
//        // Сохраняем пользователя и новость в базе данных
//        userService.updateUser(user);
//        return ResponseEntity.ok(newsService.create(news));
//    }

}

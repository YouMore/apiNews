package com.example.apiNews.controller;
import com.example.apiNews.model.entity.News;
import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.response.NewsResponse;
import com.example.apiNews.service.NewsService;
import com.example.apiNews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moderator")
@RequiredArgsConstructor
public class ModeratorController {

    private final UserService userService;
    private final NewsService newsService;
    private final ModelMapper modelMapper;

    @GetMapping("/news")
    public ResponseEntity<List<NewsResponse>> ModeratorPanel(){
        List<News> newsList = newsService.readAll();
        List<NewsResponse> newsResponseList = newsList.stream()
                .filter(news -> Boolean.FALSE.equals(news.getIsModerated())) // Фильтрация по isModerated
                .map(news -> {
                    NewsResponse newsResponse = modelMapper.map(news, NewsResponse.class);

                    // Проверка на null
                    Users user = news.getUser();
                    if (user != null) {
                        newsResponse.setUser_id(user.getId());
                        newsResponse.setUser_login(user.getLogin());
                    } else {
                        newsResponse.setUser_id(0); // Значение по умолчанию
                        newsResponse.setUser_login("Unknown"); // Значение по умолчанию
                    }

                    return newsResponse;
                }).collect(Collectors.toList());

        return new ResponseEntity<>(newsResponseList, HttpStatus.OK);
    }

    @PutMapping("/news/confirm/{id}")
    public ResponseEntity<NewsResponse> moderateConfirmNews(@PathVariable Long id) {
        News news = newsService.updateModerationStatus(id, true);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        NewsResponse newsResponse = modelMapper.map(news, NewsResponse.class);
        Users user = news.getUser();
        if (user != null) {
            newsResponse.setUser_id(user.getId());
            newsResponse.setUser_login(user.getLogin());
        } else {
            newsResponse.setUser_id(0); // Значение по умолчанию
            newsResponse.setUser_login("Unknown"); // Значение по умолчанию
        }

        return new ResponseEntity<>(newsResponse, HttpStatus.OK);
    }

    @PutMapping("/news/reject/{id}")
    public ResponseEntity<NewsResponse> moderateRejectNews(@PathVariable Long id) {
        News news = newsService.updateModerationStatus(id, false);
        if (news == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        NewsResponse newsResponse = modelMapper.map(news, NewsResponse.class);
        Users user = news.getUser();
        if (user != null) {
            newsResponse.setUser_id(user.getId());
            newsResponse.setUser_login(user.getLogin());
        } else {
            newsResponse.setUser_id(0); // Значение по умолчанию
            newsResponse.setUser_login("Unknown"); // Значение по умолчанию
        }

        return new ResponseEntity<>(newsResponse, HttpStatus.OK);
    }

}

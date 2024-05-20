package com.example.apiNews.controller;

import com.example.apiNews.model.entity.News;
import com.example.apiNews.model.entity.Users;
import com.example.apiNews.model.response.NewsResponse;
import com.example.apiNews.service.NewsService;
import com.example.apiNews.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final ModelMapper modelMapper;
    private final UserService userService;

//    @PostMapping("/create")
//    public ResponseEntity<News> create(@RequestBody NewsDTO dto){
//        News news = modelMapper.map(dto,News.class);
//        return new ResponseEntity<>(newsService.create(news), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<NewsResponse>> readAll(){
        List<News> newsList = newsService.readAll();
        List<NewsResponse> newsResponseList = newsList.stream().map(news -> {
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

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> readById(@PathVariable Long id){
        NewsResponse newsResponse = modelMapper.map(newsService.readById(id), NewsResponse.class);
        System.out.println(newsResponse);
        newsResponse.setUser_id(newsService.readById(id).getUser().getId());
        newsResponse.setUser_login(newsService.readById(id).getUser().getLogin());
        return new ResponseEntity<>(newsResponse, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<News> update(@RequestBody News news){
        return new ResponseEntity<>(newsService.update(news), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<News>> getAllNewsByUserId(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id).getNews());
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        newsService.delete(id);
        return HttpStatus.OK;
    }


}

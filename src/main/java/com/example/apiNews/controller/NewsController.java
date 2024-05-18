package com.example.apiNews.controller;

import com.example.apiNews.dto.request.NewsDTO;
import com.example.apiNews.model.entity.News;
import com.example.apiNews.service.NewsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<News> create(@RequestBody NewsDTO dto){
        News news = modelMapper.map(dto,News.class);
        return new ResponseEntity<>(newsService.create(news), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<News>> readAll(){
        return new ResponseEntity<>(newsService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> readById(@PathVariable Long id){
        return new ResponseEntity<>(newsService.readById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<News> update(@RequestBody News news){
        return new ResponseEntity<>(newsService.update(news), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        newsService.delete(id);
        return HttpStatus.OK;
    }


}

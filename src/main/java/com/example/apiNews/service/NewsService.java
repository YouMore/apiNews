package com.example.apiNews.service;

import com.example.apiNews.model.entity.News;
import com.example.apiNews.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public News create(News news){
        System.out.println("Создан пост");
        news.setIsModerated(false);
        return newsRepository.save(news);
    }

    public List<News> readAll(){
        return newsRepository.findAll();
    }

    public News readById(Long id){
        return newsRepository.findById(id).orElse(null);
    }

    public News update(News news){
        return newsRepository.save(news);
    }

    public void delete(Long id){
        newsRepository.deleteById(id);
    }
}

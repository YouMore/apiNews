package com.example.apiNews.repository;


import com.example.apiNews.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitle(String title);
    List<News> findByGame(String Game);

}

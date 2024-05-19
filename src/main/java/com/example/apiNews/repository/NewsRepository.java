package com.example.apiNews.repository;


import com.example.apiNews.model.entity.News;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitle(String title);
    List<News> findByGame(String Game);
    @Transactional
    @Modifying
    @Query("DELETE FROM News n WHERE n.user.id = :userId")
    void deleteByUserId(@Param("userId") long userId);
}

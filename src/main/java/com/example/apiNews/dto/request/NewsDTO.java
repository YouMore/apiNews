package com.example.apiNews.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {
    private String title;
    private String description;
    private LocalDate date;
    private String game;
}

package com.example.apiNews.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateNewsRequest {
    private String title;

    private String description;

    private LocalDate date;

    private String game;
}

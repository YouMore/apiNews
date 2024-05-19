package com.example.apiNews.model.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    private long id;
    private String title;
    private String description;
    private LocalDate date;
    private String game;
    private Boolean isModerated;
    private long user_id;
    private String user_login;
}

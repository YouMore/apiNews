package com.example.apiNews.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS to all endpoints
                .allowedOrigins("http://localhost:3000") // Allow this origin to access the endpoints
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowable methods
                .allowedHeaders("*")// Allow all headers
                .allowCredentials(true); // Allow credentials
    }
}
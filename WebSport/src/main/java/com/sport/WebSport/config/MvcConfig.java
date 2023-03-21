package com.sport.WebSport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sport").setViewName("sport");
        registry.addViewController("/").setViewName("sport");
        registry.addViewController("/sport/add").setViewName("sport");
        registry.addViewController("/login").setViewName("login");
    }

}
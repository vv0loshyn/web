package com.sport.WebSport.service;

import com.sport.WebSport.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
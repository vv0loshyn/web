package com.sport.WebSport.service;

import com.sport.WebSport.models.Post;

import java.util.*;

public interface PostService {
    List<Post> getAllPosts();

    void savePost(Post post);

    void deletePost(long id);

    Optional<Post> findPostById(long id);

    List<Post> searchPosts(String team);
}
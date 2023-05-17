package com.sport.WebSport.service;

import com.sport.WebSport.models.Post;
import com.sport.WebSport.repo.PostRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Optional<Post> findPostById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> searchPosts(String team) {
        return postRepository.findByTeam1OrTeam2(team, team);
    }
}
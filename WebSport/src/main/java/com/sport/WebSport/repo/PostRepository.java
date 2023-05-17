package com.sport.WebSport.repo;

import com.sport.WebSport.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByTeam1OrTeam2(String team1, String team2);
}

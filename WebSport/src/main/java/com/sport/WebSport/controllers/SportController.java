package com.sport.WebSport.controllers;

import com.sport.WebSport.models.Post;
import com.sport.WebSport.models.Result;
import com.sport.WebSport.service.PostService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Controller
@RequestMapping("/sport")
public class SportController {

    private final PostService postService;

    public SportController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping()
    public String sportMain(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "sport-main";
    }

    @GetMapping("/{id}")
    public String sportInfo(@PathVariable(value = "id") long id, Model model) {
        Optional<Post> post = postService.findPostById(id);
        if (post.isEmpty()) {
            return "redirect:/sport";
        }
        model.addAttribute("post", post.get());
        return "sport-info";
    }
    @GetMapping("/search")
    public String sportSearch(@RequestParam(name = "team") String team, Model model) {
        List<Post> posts = postService.searchPosts(team);
        model.addAttribute("posts", posts);
        return "sport-main";
    }
}

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

    @GetMapping("/info/add")
    public String sportAdd(@ModelAttribute("post") Post post) {
        return "sport-add";
    }

    @PostMapping("/info/add")
    public String sportPostAdd(@ModelAttribute("post") @Valid Post post,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sport-add";
        if (post.getTeam1().equals(post.getTeam2())) {
            bindingResult.rejectValue("team2", "error.post", "Назва другої команди не повинна співпадати з назвою першої команди");
            return "sport-add";
        }
        Result result = new Result(post.getRes1(), post.getRes2());
        post.setResult(result);
        postService.savePost(post);
        return "redirect:/sport";
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

    @GetMapping("/{id}/edit")
    public String sportGetUpdate(Model model, @PathVariable("id") long id) {
        Optional<Post> post = postService.findPostById(id);
        if (post.isEmpty()) {
            return "redirect:/sport";
        }
        model.addAttribute("post", post.get());
        return "sport-edit";
    }

    @PostMapping("/{id}/edit")
    public String sportPostUpdate(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult,
                                  @PathVariable("id") long id, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "sport-edit";
        }
        if (post.getTeam1().equals(post.getTeam2())) {
            bindingResult.rejectValue("team2", "error.post", "Назва другої команди не повинна співпадати з назвою першої команди");
            return "sport-edit";
        }
        Result result = new Result(post.getRes1(), post.getRes2());
        post.setResult(result);
        postService.savePost(post);
        return "redirect:/sport/{id}";
    }

    @PostMapping("/{id}/remove")
    public String sportPostRemove(@PathVariable(value = "id") long id, Model model) {
        Post post = postService.findPostById(id).orElseThrow();
        postService.deletePost(id);
        return "redirect:/sport";
    }

    @GetMapping("/search")
    public String sportSearch(@RequestParam(name = "team") String team, Model model) {
        List<Post> posts = postService.searchPosts(team);
        model.addAttribute("posts", posts);
        return "sport-main";
    }
}

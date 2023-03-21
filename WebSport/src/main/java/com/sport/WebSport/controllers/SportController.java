package com.sport.WebSport.controllers;

import com.sport.WebSport.models.Post;
import com.sport.WebSport.repo.PostRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SportController {

    private final PostRepository postRepository;

    public SportController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/sport")
    public String sportMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "sport-main";
    }
    @GetMapping("/sport/add")
    public String sportAdd(@ModelAttribute("post") Post post) {
        return "sport-add";
    }
    @PostMapping("/sport/add")
    public String sportPostAdd(@ModelAttribute("post") @Valid Post post,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sport-add";
        if (post.getTeam1().equals(post.getTeam2())) {
            bindingResult.rejectValue("team2", "error.post", "Назва другої команди не повинна співпадати з назвою першої команди");
            return "sport-add";
        }
        postRepository.save(post);
        return "redirect:/sport";
    }
    @GetMapping("/sport/{id}")
    public String sportInfo(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/sport";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "sport-info";
    }

    @GetMapping("/sport/{id}/edit")
    public String sportGetUpdate(Model model, @PathVariable("id") long id) {
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "sport-edit";
    }

    @PostMapping("/sport/{id}/edit")
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
        postRepository.save(post);
        return "redirect:/sport";
    }
    @PostMapping("/sport/{id}/remove")
    public String sportPostRemove(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/sport";
    }
    @GetMapping("/sport/search")
    public String sportSearch(@RequestParam(name = "team") String team, Model model) {
        List<Post> posts = postRepository.findByTeam1ContainingIgnoreCaseOrTeam2ContainingIgnoreCase(team, team);
        model.addAttribute("posts", posts);
        return "sport-main";
    }
}

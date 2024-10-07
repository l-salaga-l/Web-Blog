package com.example.webblog.controllers;

import com.example.webblog.model.Post;
import com.example.webblog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String addPost(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String createPost(@RequestParam String title, @RequestParam String author, @RequestParam String content, Model model) {
        Post post = new Post.PostBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setContent(content)
                .build();
        postRepository.save(post);
        return "redirect:/blog";
    }
}

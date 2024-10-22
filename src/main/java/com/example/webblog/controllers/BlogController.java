package com.example.webblog.controllers;

import com.example.webblog.model.Post;
import com.example.webblog.service.Impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    private final PostServiceImpl postService;

    @Autowired
    public BlogController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postService.findAll();
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
        postService.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String detailsPost(@PathVariable(value = "id") long id, Model model) {
        return getPost(id, model, "blog-details");
    }

    @GetMapping("/blog/{id}/edit")
    public String editPost(@PathVariable(value = "id") long id, Model model) {
        return getPost(id, model, "blog-edit");
    }

    @PostMapping("/blog/{id}/edit")
    public String updatePost(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String author, @RequestParam String content, Model model) {
        Post post = postService.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAuthor(author);
        post.setContent(content);
        postService.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String removePost(@PathVariable(value = "id") long id, Model model) {
        Post post = postService.findById(id).orElseThrow();
        postService.delete(post);
        return "redirect:/blog";
    }

    private String getPost(long id, Model model, String viewName) {
        Post post = postService.findById(id).orElse(null);
        if (post == null) {
            return "redirect:/blog";
        }
        model.addAttribute("post", post);
        return viewName;
    }
}
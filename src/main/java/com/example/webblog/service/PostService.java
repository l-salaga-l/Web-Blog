package com.example.webblog.service;

import com.example.webblog.model.Post;

import java.util.Optional;

public interface PostService {

    boolean existsById(Long id);

    Optional<Post> findById(Long id);

    void save(Post post);

    void delete(Post post);

    Iterable<Post> findAll();
}

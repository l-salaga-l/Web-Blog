package com.example.webblog.model;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    @Column(name = "content", columnDefinition = "TEXT", length = 100000)
    private String content;

    private int views;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class PostBuilder {
        private Post post;

        public PostBuilder() {
            post = new Post();
        }
        public PostBuilder setTitle(String title) {
            post.setTitle(title);
            return this;
        }

        public PostBuilder setAuthor(String author) {
            post.setAuthor(author);
            return this;
        }

        public PostBuilder setContent(String content) {
            post.setContent(content);
            return this;
        }

        public PostBuilder setViews(int views) {
            post.setViews(views);
            return this;
        }

        public Post build() {
            return post;
        }
    }
}

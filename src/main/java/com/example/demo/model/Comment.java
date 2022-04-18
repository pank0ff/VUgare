package com.example.demo.model;
import javax.persistence.*;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,updatable = false)
    private User author;

    public Post getPost() {
        return post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPost(Post message) {
        this.post = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
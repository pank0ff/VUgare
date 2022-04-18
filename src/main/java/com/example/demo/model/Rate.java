package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Rate {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int rate;

    public Rate() {

    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
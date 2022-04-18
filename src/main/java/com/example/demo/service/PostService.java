package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final PostRepo postRepo;
    @Autowired
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public void addPost(Post post){
        postRepo.save(post);
    }

    public List<Post> findAllPosts(){
        return postRepo.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }
}

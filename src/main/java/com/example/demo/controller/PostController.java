package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.CommentRepo;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.PostService;
import com.example.demo.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dwnzejl4h",
            "api_key", "424976915584458",
            "api_secret", "TlQsPJt2OHBBSJVzwe31u3zFqgY"));

    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final RateService rateService;
    private final PostService postService;

    @Autowired
    public PostController(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo, RateService rateService,PostService postService) {
        this.postRepo = postRepo;
        this.postService = postService;
        this.rateService = rateService;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/all")
    public List<Post> getAllPosts(){
        return postService.findAllPosts();
    }
    @PostMapping("/add")
    public void addPost(@RequestBody Post post){
        postService.addPost(post);
    }
    @GetMapping("{id}")
    public Optional<Post> getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

}

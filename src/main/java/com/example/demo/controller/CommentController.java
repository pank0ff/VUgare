package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepo;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final PostRepo postRepo;

    @Autowired
    public CommentController(CommentService commentService, PostRepo postRepo) {
        this.commentService = commentService;
        this.postRepo = postRepo;
    }


    @PostMapping("{id}")
    public void create(
            @PathVariable Integer id,
            @RequestParam String text,
            @AuthenticationPrincipal User user
    ){
        Post post = postRepo.findById(id);
        commentService.create(post,text,user);
    }
}

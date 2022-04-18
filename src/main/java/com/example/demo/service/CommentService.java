package com.example.demo.service;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo){
        this.commentRepo = commentRepo;
    }

    public void create(Post post, String text, User user) {
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setText(text);
        comment.setAuthor(user);
        commentRepo.save(comment);
    }

}
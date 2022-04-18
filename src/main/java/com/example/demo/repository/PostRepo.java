package com.example.demo.repository;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findAll();

    List<Post> findByName(String filter);

    Post findById(Integer id);

    List<Post> findByAuthor(User user);

    List<Post> findByHashtag(String hashtag);
}

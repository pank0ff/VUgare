package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.CommentRepo;
import com.example.demo.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    @Autowired
    public PostService(PostRepo postRepo, CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
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

    public List<Post> filter(String filter){
        List<Post> posts = postRepo.findAll();
        List<Post> filterPosts = postRepo.findAll();
        filterPosts.clear();
        if(filter != null && !filter.isEmpty()){
            for(Post post : posts){
                boolean flag = false;
                if(post.getText().contains(filter)){
                    flag = true;
                }else{
                    if(post.getName().contains(filter)){
                        flag = true;
                    }else{
                        if(post.getHashtag().contains(filter)){
                            flag = true;
                        }else{
                            if(post.getName().contains(filter)){
                                flag = true;
                            }if(post.getName().contains(filter)){
                                flag = true;
                            }else{
                                for(Comment comment : commentRepo.findByPostId(post.getId())){
                                    if (comment.getText().contains(filter)) {
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(flag){
                    filterPosts.add(post);
                }
            }
            posts = filterPosts;
        }
        return posts;
    }
}

package com.example.demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PostRepo postRepo;

    @Autowired
    public UserService(UserRepo userRepo,PostRepo postRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public void addUser(User user){
        userRepo.save(user);
    }

    public Optional<User> findUserById(Long id){
        return userRepo.findById(id);
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    public void updateUser(Long id,User user)  {
        User currentUser = userRepo.getById(id);
        if(!Objects.equals(currentUser.getUserpic(),user.getUserpic())) {
            currentUser.setUserpic(user.getUserpic());
        }
        if(!Objects.equals(currentUser.getUsername(),user.getUsername())) {
            currentUser.setUsername(user.getUsername());
        }
        if(!Objects.equals(currentUser.getLocation(),user.getLocation())) {
            currentUser.setLocation(user.getLocation());
        }
        if(!Objects.equals(currentUser.getBirthday(),user.getBirthday())) {
            currentUser.setBirthday(user.getBirthday());
        }
        if(!Objects.equals(currentUser.getAboutMyself(),user.getAboutMyself())) {
            currentUser.setAboutMyself(user.getAboutMyself());
        }
        if(!Objects.equals(currentUser.getPassword(),user.getPassword())) {
            currentUser.setPassword(user.getPassword());
        }
        if(!Objects.equals(currentUser.getEmail(),user.getEmail())) {
            currentUser.setEmail(user.getEmail());
        }
        if(!Objects.equals(currentUser.getChoice(),user.getChoice())) {
            currentUser.setChoice(user.getChoice());
        }
        if(!Objects.equals(currentUser.getGender(),user.getGender())) {
            currentUser.setGender(user.getGender());
        }
        if(!Objects.equals(currentUser.getTheme(),user.getTheme())) {
            currentUser.setTheme(user.getTheme());
        }
        if(!Objects.equals(currentUser.getLinkDribble(),user.getLinkDribble())) {
            currentUser.setLinkDribble(user.getLinkDribble());
        }
        if(!Objects.equals(currentUser.getLinkFacebook(),user.getLinkFacebook())) {
            currentUser.setLinkFacebook(user.getLinkFacebook());
        }
        if(!Objects.equals(currentUser.getLinkGoogle(),user.getLinkGoogle())) {
            currentUser.setLinkGoogle(user.getLinkGoogle());
        }
        if(!Objects.equals(currentUser.getLinkLinkedIn(),user.getLinkLinkedIn())) {
            currentUser.setLinkLinkedIn(user.getLinkLinkedIn());
        }
        if(!Objects.equals(currentUser.getLinkYoutube(),user.getLinkYoutube())) {
            currentUser.setLinkYoutube(user.getLinkYoutube());
        }
        userRepo.save(currentUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepo.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().removeIf(user1 -> Objects.equals(user1.getUsername(), currentUser.getUsername()));
        userRepo.save(user);
    }

    public void like(User user, Post post) {
        post.getLikes().add(user);
        postRepo.save(post);
    }

    public void unlike(User user, Post post) {
        post.getLikes().removeIf(user1 -> Objects.equals(user1.getUsername(), user.getUsername()));
        postRepo.save(post);
    }
}

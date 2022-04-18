package com.example.demo.controller;

import com.example.demo.exceptions.UserNotExistException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepo userRepo;

    private final UserService userService;

    @Autowired
    public UserController(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "user has added";
    }

    @GetMapping("{id}")
    public Optional<User> getProfileOfUser(@PathVariable Long id){
        userRepo.findById(id).orElseThrow(UserNotExistException::new);
        return userService.findUserById(id);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "user with id " +id +" delete";
    }

    @PostMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) throws IOException {
        userRepo.findById(id).orElseThrow(UserNotExistException::new);
            userService.updateUser(id,user);
    }

}

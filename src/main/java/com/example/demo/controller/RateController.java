package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.model.Rate;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepo;
import com.example.demo.repository.RateRepo;
import com.example.demo.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rate")
public class RateController {

    private final RateService rateService;
    private final PostRepo postRepo;
    private final RateRepo rateRepo;

    @Autowired
    public RateController(RateService rateService, PostRepo postRepo, RateRepo rateRepo) {
        this.rateService = rateService;
        this.postRepo = postRepo;
        this.rateRepo = rateRepo;
    }


    @PostMapping("/{id}")
    public void create(@PathVariable Integer id, @AuthenticationPrincipal User user, @RequestParam int rate){
        Post post = postRepo.findById(id);
        List<Rate> ratesByUserId = rateRepo.findByUserId(user.getId());
        int counter =0;
        for(Rate rate1 : ratesByUserId){
            if(Objects.equals(rate1.getPost(), post)){
                counter++;
            }
        }
        if (counter == 0) {
            rateService.createRate(rate, user, post);
        }
    }
}

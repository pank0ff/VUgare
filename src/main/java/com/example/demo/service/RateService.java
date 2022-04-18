package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.Rate;
import com.example.demo.model.User;
import com.example.demo.repository.RateRepo;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {
    private final RateRepo rateRepo;

    @Autowired
    public RateService(RateRepo rateRepo){
        this.rateRepo = rateRepo;
    }

    public void createRate(int num, User user, Post post){
        Rate rate = new Rate();
        rate.setRate(num);
        rate.setUser(user);
        rate.setPost(post);
        rateRepo.save(rate);
    }
    public double calcAverageRate(Post post){
        float counter = 0;
        float sum = 0;
        double averageRate = 0;
        Integer i;
        List<Rate> rates  = rateRepo.findByPostId(Math.toIntExact(post.getId()));
        for(Rate rate : rates){
            sum+=rate.getRate();
            counter++;
        }
        if(counter != 0){
            averageRate = sum / counter;}else{
            averageRate = 0;
        }
        DoubleRounder.round(averageRate,2);
        return averageRate;
    }

}
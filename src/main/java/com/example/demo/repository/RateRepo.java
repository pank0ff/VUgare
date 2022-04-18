package com.example.demo.repository;

import com.example.demo.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepo extends JpaRepository<Rate, Long> {

    List<Rate> findAll();

    List<Rate> findByUserId(Long id);

    Rate findById(Integer id);


    List<Rate> findByPostId(Integer id);

}
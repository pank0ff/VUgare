package com.example.demo.repository;

import com.example.demo.model.User;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    List<User> findAll();
    Optional<User> findById(Long id);

    UserDetails findByUsername(String username);
}

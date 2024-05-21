package com.jeanlima.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.user.domain.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    User findByEmail(String email);
}

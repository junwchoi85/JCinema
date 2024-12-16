package com.jcinema.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcinema.users.entity.Users;


public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Users findByUsernameAndPassword(String username, String password);
    Users findByEmailAndPassword(String email, String password);
    
}

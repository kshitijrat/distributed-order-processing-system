package com.project.backend.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
package org.polytech.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.polytech.covid.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  
}
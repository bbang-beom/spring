package com.ticket_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket_reservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}

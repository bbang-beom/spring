package com.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservation.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);
    List<Event> findByNameContainingIgnoreCase(String keyword);
}

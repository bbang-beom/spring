package com.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservation.entity.Event;
import com.reservation.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByEventNameContainingIgnoreCase(String keyword);
    Optional<Favorite> findByEvent(Event event);
}

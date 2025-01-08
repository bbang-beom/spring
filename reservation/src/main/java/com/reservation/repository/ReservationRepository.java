package com.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservation.entity.Event;
import com.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByEvent(Event event);
    List<Reservation> findByEvent_NameContainingIgnoreCase(String keyword);
}

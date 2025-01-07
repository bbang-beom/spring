package com.ticket_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket_reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

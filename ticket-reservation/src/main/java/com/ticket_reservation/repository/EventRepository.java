package com.ticket_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket_reservation.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
}

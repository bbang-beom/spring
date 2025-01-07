package com.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ticket.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
	Ticket findByEventName(String eventName);
}

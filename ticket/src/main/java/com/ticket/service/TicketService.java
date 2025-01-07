package com.ticket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.entity.Ticket;
import com.ticket.repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(String eventName, int totalSeats, LocalDateTime performanceDate, LocalDateTime bookingOpenTime) {
        Ticket ticket = new Ticket();
        ticket.setEventName(eventName);
        ticket.setTotalSeats(totalSeats);
        ticket.setRemainingSeats(totalSeats);
        ticket.setPerformanceDate(performanceDate);
        ticket.setBookingOpenTime(bookingOpenTime);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public void reserveTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        if (ticket.getRemainingSeats() > 0 && LocalDateTime.now().isAfter(ticket.getBookingOpenTime())) {
            ticket.setRemainingSeats(ticket.getRemainingSeats() - 1);
            ticketRepository.save(ticket);
        } else {
            throw new IllegalStateException("Cannot reserve ticket. Either no seats available or booking is not open yet.");
        }
    }

    public void cancelReservation(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setRemainingSeats(ticket.getRemainingSeats() + 1);
        ticketRepository.save(ticket);
    }
}

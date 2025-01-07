package com.ticket.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.entity.Ticket;
import com.ticket.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestParam String eventName,
                                               @RequestParam int totalSeats,
                                               @RequestParam LocalDateTime performanceDate,
                                               @RequestParam LocalDateTime bookingOpenTime) {
        Ticket ticket = ticketService.createTicket(eventName, totalSeats, performanceDate, bookingOpenTime);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.findAllTickets());
    }

    @PostMapping("/{ticketId}/reserve")
    public ResponseEntity<String> reserveTicket(@PathVariable Long ticketId) {
        ticketService.reserveTicket(ticketId);
        return ResponseEntity.ok("Reservation successful!");
    }

    @PostMapping("/{ticketId}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable Long ticketId) {
        ticketService.cancelReservation(ticketId);
        return ResponseEntity.ok("Reservation cancelled!");
    }
}

package com.ticket.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticket.entity.Ticket;
import com.ticket.service.TicketService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final TicketService ticketService;

    public ReservationController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String viewReservations(Model model, Principal principal) {
        List<Ticket> tickets = ticketService.getUserTickets(principal.getName());
        model.addAttribute("tickets", tickets);
        return "reservations";
    }

    @PostMapping("/cancel/{ticketId}")
    public String cancelTicket(@PathVariable Long ticketId, Principal principal) {
        ticketService.cancelTicket(ticketId, principal.getName());
        return "redirect:/reservations";
    }
}

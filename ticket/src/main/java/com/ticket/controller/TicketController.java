package com.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticket.entity.Ticket;
import com.ticket.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/new")
    public String showBookingForm(Model model) {
        Ticket ticket = new Ticket();
        ticket.setTotalSeats(100);  // 기본 좌석 수 설정
        ticket.setRemainingSeats(100); 
        model.addAttribute("ticket", ticket);
        return "ticket-form";
    }

    @PostMapping("/book")
    public String bookTicket(@ModelAttribute Ticket ticket, Model model) {
        try {
            ticketService.bookTicket(ticket);
            return "redirect:/tickets/success";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "ticket-form";
        }
    }
}

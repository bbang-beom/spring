package com.ticket_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.ticket_reservation.service.ReservationService;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reserve")
    public String makeReservation(Long userId, Long eventId, Model model) {
        reservationService.makeReservation(userId, eventId);
        model.addAttribute("message", "Reservation successful!");
        return "reservation-success";
    }
}

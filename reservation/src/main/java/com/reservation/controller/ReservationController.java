package com.reservation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reservation.entity.Event;
import com.reservation.entity.Reservation;
import com.reservation.repository.EventRepository;
import com.reservation.repository.ReservationRepository;
import com.reservation.service.ReservationService;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository; 

    @Autowired
    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository, EventRepository eventRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
    }
    
    @GetMapping("/")
    public String showEventList(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);  
        return "index";  
    }

    @GetMapping("/events/{eventId}")
    public String showEventDetails(@PathVariable Long eventId, Model model) {
        Event event = reservationService.getEventDetails(eventId);
        model.addAttribute("event", event);
        return "eventDetails";
    }
    
    @PostMapping("/reserve/{eventId}")
    public String processReservation(@PathVariable Long eventId, @RequestParam int tickets, Model model) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            if (event.getRemainingSeats() >= tickets) {
                Reservation reservation = new Reservation("Anonymous", "test@example.com", tickets, event);
                reservationRepository.save(reservation);

                event.setRemainingSeats(event.getRemainingSeats() - tickets);
                eventRepository.save(event);

                model.addAttribute("reservation", reservation);
                model.addAttribute("event", event);

                return "reservationResult";  
            } else {
               
                return "error";  
            }
        }
        return "redirect:/"; 
    }

    @GetMapping("/reserve/{eventId}")
    public String showReservationPage(@PathVariable Long eventId, Model model) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());
            return "reservation";
        } else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/my-reservations")
    public String showMyReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();  
        model.addAttribute("reservations", reservations);
        return "myReservations";  
    }
    
    @PostMapping("/cancel-reservation/{reservationId}")
    public String cancelReservation(@PathVariable Long reservationId, Model model) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            Event event = reservation.getEvent();

            event.setRemainingSeats(event.getRemainingSeats() + reservation.getTickets());
            eventRepository.save(event);

            reservationRepository.delete(reservation);

            model.addAttribute("message", "Reservation has been successfully canceled.");
        } else {
            model.addAttribute("message", "Reservation not found.");
        }

        return "redirect:/my-reservations"; 
    }
    
    @GetMapping("/search")
    public String searchEvents(@RequestParam String keyword, Model model) {
        List<Event> searchResults = eventRepository.findByNameContainingIgnoreCase(keyword);
        model.addAttribute("events", searchResults);
        return "index";
    }
    
    @GetMapping("/my-reservations/search")
    public String searchMyReservations(@RequestParam String keyword, Model model) {
        List<Reservation> searchResults = reservationRepository.findByEvent_NameContainingIgnoreCase(keyword);
        model.addAttribute("reservations", searchResults);
        return "myreservations";
    }
}

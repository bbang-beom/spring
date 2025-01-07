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
    
 // 예약 처리 후 결과 페이지로 이동
    @PostMapping("/reserve/{eventId}")
    public String processReservation(@PathVariable Long eventId, @RequestParam int tickets, Model model) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            if (event.getRemainingSeats() >= tickets) {
                // 예매 가능하면 예약 처리
                Reservation reservation = new Reservation("Anonymous", "test@example.com", tickets, event);
                reservationRepository.save(reservation);

                // 남은 좌석 수 업데이트
                event.setRemainingSeats(event.getRemainingSeats() - tickets);
                eventRepository.save(event);

                // 예약 결과를 모델에 전달
                model.addAttribute("reservation", reservation);
                model.addAttribute("event", event);

                return "reservationResult";  // 예약 결과 페이지로 이동
            } else {
                // 예매 불가능한 경우
                return "error";  // 예매 불가 페이지로 이동 (선택 사항)
            }
        }
        return "redirect:/";  // 이벤트가 없으면 홈으로 리다이렉트
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
}

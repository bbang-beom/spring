package com.reservation.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.entity.Event;
import com.reservation.entity.Reservation;
import com.reservation.repository.EventRepository;
import com.reservation.repository.ReservationRepository;

@Service
public class ReservationService {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;

    public Event getEventDetails(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void makeReservation(Reservation reservation, Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getRemainingSeats() < reservation.getTickets()) {
            throw new RuntimeException("Not enough remaining seats");
        }

        event.setRemainingSeats(event.getRemainingSeats() - reservation.getTickets());
        eventRepository.save(event);
        
        reservation.setEvent(event);
        reservation.setReservationTime(LocalDateTime.now());
        reservationRepository.save(reservation);
    }
}
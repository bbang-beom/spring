package com.ticket_reservation.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket_reservation.entity.Event;
import com.ticket_reservation.entity.Reservation;
import com.ticket_reservation.entity.User;
import com.ticket_reservation.repository.EventRepository;
import com.ticket_reservation.repository.ReservationRepository;
import com.ticket_reservation.repository.UserRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public void makeReservation(Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElse(null);
        Event event = eventRepository.findById(eventId).orElse(null);
        if (user != null && event != null && event.getRemainingSeats() > 0) {
            Reservation reservation = new Reservation(user, event, LocalDateTime.now());
            reservationRepository.save(reservation);
            event.setRemainingSeats(event.getRemainingSeats() - 1);
            eventRepository.save(event);
        }
    }
}

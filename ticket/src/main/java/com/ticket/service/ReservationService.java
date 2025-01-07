package com.ticket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.entity.Reservation;
import com.ticket.entity.Ticket;
import com.ticket.entity.User;
import com.ticket.repository.ReservationRepository;
import com.ticket.repository.TicketRepository;
import com.ticket.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Reservation createReservation(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (ticket.getRemainingSeats() > 0 && LocalDateTime.now().isAfter(ticket.getBookingOpenTime())) {
            ticket.setRemainingSeats(ticket.getRemainingSeats() - 1);
            Reservation reservation = new Reservation();
            reservation.setTicket(ticket);
            reservation.setUser(user);
            reservation.setReservationTime(LocalDateTime.now());
            ticketRepository.save(ticket);
            return reservationRepository.save(reservation);
        } else {
            throw new IllegalStateException("Cannot make reservation. No available seats or booking not open.");
        }
    }

    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found."));
        
        Ticket ticket = reservation.getTicket();
        ticket.setRemainingSeats(ticket.getRemainingSeats() + 1);
        ticketRepository.save(ticket);
        reservationRepository.delete(reservation);
    }
}
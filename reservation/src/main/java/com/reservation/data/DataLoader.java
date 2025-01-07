package com.reservation.data;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.reservation.entity.Event;
import com.reservation.entity.Reservation;
import com.reservation.repository.EventRepository;
import com.reservation.repository.ReservationRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public DataLoader(EventRepository eventRepository, ReservationRepository reservationRepository) {
        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Event event1 = new Event("Concert A", 100, 70, LocalDateTime.of(2025, 1, 10, 12, 0), "image1.jpg");
        Event event2 = new Event("Concert B", 150, 120, LocalDateTime.of(2025, 2, 20, 1, 0), "image2.jpg");
        Event event3 = new Event("Concert C", 200, 140, LocalDateTime.of(2025, 3, 30, 2, 0), "image3.jpg");
        
        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);

        Reservation reservation1 = new Reservation("John Doe", "john.doe@example.com", 2, event1);
        Reservation reservation2 = new Reservation("Jane Smith", "jane.smith@example.com", 1, event2);
        Reservation reservation3 = new Reservation("Alice Johnson", "alice.johnson@example.com", 3, event3);

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);

        System.out.println("Test data has been loaded.");
    }
}

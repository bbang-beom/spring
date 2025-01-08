package com.reservation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int tickets;
    private LocalDateTime reservationTime;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Reservation() {}

    public Reservation(String name, String email, int tickets, Event event) {
        this.name = name;
        this.email = email;
        this.tickets = tickets;
        this.event = event;
        this.reservationTime = LocalDateTime.now();
    }

}

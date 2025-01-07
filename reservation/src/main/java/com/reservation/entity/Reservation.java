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

    // 기본 생성자 (JPA에서 필요)
    public Reservation() {}

    // 파라미터화된 생성자 (Lombok @AllArgsConstructor 또는 수동 생성)
    public Reservation(String name, String email, int tickets, Event event) {
        this.name = name;
        this.email = email;
        this.tickets = tickets;
        this.event = event;
        this.reservationTime = LocalDateTime.now();
    }

}

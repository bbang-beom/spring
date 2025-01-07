package com.ticket.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String eventName;
    @Column(nullable = false)
    private int totalSeats;
    @Column(nullable = false)
    private int remainingSeats;
    @Column(nullable = false)
    private LocalDateTime performanceDate;
    @Column(nullable = false)
    private LocalDateTime bookingOpenTime;
}

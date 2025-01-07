package com.reservation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int totalSeats;
    private int remainingSeats;
    private LocalDateTime performanceDate;
    private String image;
    
    public Event(String name, int totalSeats, int remainingSeats, LocalDateTime performanceDate, String image) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.remainingSeats = remainingSeats;
        this.performanceDate = performanceDate;
        this.image = image;
    }
}

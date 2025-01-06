package com.ticket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.entity.Ticket;
import com.ticket.repository.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getUserTickets(String username) {
        return ticketRepository.findByUserUsername(username);
    }

    public void cancelTicket(Long ticketId, String username) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("해당 티켓을 찾을 수 없습니다."));
        
        if (!ticket.getUser().getUsername().equals(username)) {
            throw new SecurityException("본인의 티켓만 취소할 수 있습니다.");
        }
        
        ticket.setRemainingSeats(ticket.getRemainingSeats() + ticket.getNumberOfTickets());
        ticketRepository.delete(ticket);
    }
}

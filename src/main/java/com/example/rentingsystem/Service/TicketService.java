package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.Ticket;
import com.example.rentingsystem.Repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void removeTicket(Integer ticketId){
        Ticket ticket1 = ticketRepository.findTicketById(ticketId);
        if (ticket1 == null){
            throw new ApiException("Could not find ticket");
        }
        ticketRepository.delete(ticket1);
    }

    public void updateTicket(Ticket ticket, Integer ticketId){
        Ticket ticket1 = ticketRepository.findTicketById(ticketId);
        if (ticket1 == null){
            throw new ApiException("Could not find ticket");
        }
        ticket1.setTicketStatus(ticket.getTicketStatus());
        ticket1.setDescription(ticket.getDescription());
        ticket1.setTicketType(ticket.getTicketType());
        ticketRepository.save(ticket1);
    }

}

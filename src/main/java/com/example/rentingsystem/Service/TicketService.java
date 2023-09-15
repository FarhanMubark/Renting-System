package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.*;
import com.example.rentingsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final LessorRepository lessorRepository;
    private final RenterRepository renterRepository;



    public List<Ticket> getTickets(){


        return ticketRepository.findAll();
    }


    public Ticket getTicketById(Integer id){
        Ticket ticket = ticketRepository.findTicketById(id);

        if (ticket == null){
            throw new ApiException("Ticket Not Found");
        }
        return ticket;
    }



        public void addLessorTickets(Integer user_id, Ticket ticket){
        Lessor lessor = lessorRepository.findLessorById(user_id);

            ticket.setLessor(lessor);
            ticketRepository.save(ticket);
    }

    public List<Ticket> getLessorTickets(Integer lessor_id){
        Lessor lessor = lessorRepository.findLessorById(lessor_id);

        return ticketRepository.findAllByLessor(lessor);
    }

    public void deleteLessorTickets(Integer user_id, Integer ticket_id){
        Lessor lessor = lessorRepository.findLessorById(user_id);
        Ticket ticket = ticketRepository.findTicketById(ticket_id);

        if (ticket != null && Objects.equals(lessor.getId(), ticket.getLessor().getId())){
            ticketRepository.delete(ticket);
        } else {
            throw new ApiException("Not Found");
        }
    }

    public void resolveTicket(Integer ticket_id){
        Ticket ticket = ticketRepository.findTicketById(ticket_id);

        if (ticket == null){
            throw new ApiException("Ticket Not Found");
        }

        ticket.setTicketStatus("non-active");
        ticketRepository.save(ticket);
    }

        public void addRenterTicket(Integer user_id, Ticket ticket){
        Renter renter = renterRepository.findRenterById(user_id);

                ticket.setRenter(renter);
                ticketRepository.save(ticket);

    }

    public List<Ticket> getRenterTickets(Integer renter_id){
        Renter renter = renterRepository.findRenterById(renter_id);

        return ticketRepository.findAllByRenter(renter);
    }

    public void deleteRenterTicket(Integer user_id, Integer ticket_id){
        Renter renter = renterRepository.findRenterById(user_id);
        Ticket ticket = ticketRepository.findTicketById(ticket_id);
        if (ticket != null && Objects.equals(renter.getId(), ticket.getRenter().getId())){
            ticketRepository.delete(ticket);
        } else {
            throw new ApiException(" Not found");
        }
    }



    public void updateRenterTicket(Integer renter_id, Integer ticket_id, Ticket ticket){
        Renter renter = renterRepository.findRenterById(renter_id);
        Ticket ticket1 = ticketRepository.findTicketById(ticket_id);

       if (renter == null && ticket == null){
           throw new ApiException("Not Found");
       }

       ticket1.setTicketType(ticket.getTicketType());
       ticket1.setDescription(ticket.getDescription());
       ticketRepository.save(ticket1);
    }

    public void updateLessorTicket(Integer lessor_id, Integer ticket_id, Ticket ticket){
        Lessor lessor = lessorRepository.findLessorById(lessor_id);
        Ticket ticket1 = ticketRepository.findTicketById(ticket_id);

        if (lessor == null && ticket == null){
            throw new ApiException("Not Found");
        }

        ticket1.setTicketType(ticket.getTicketType());
        ticket1.setDescription(ticket.getDescription());
        ticketRepository.save(ticket1);
    }


}

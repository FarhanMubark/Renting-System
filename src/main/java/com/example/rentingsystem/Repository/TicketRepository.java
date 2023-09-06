package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Ticket findTicketById(Integer ticketId );

}

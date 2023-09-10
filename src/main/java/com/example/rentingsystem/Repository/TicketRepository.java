package com.example.rentingsystem.Repository;

import com.example.rentingsystem.Model.Lessor;
import com.example.rentingsystem.Model.Renter;
import com.example.rentingsystem.Model.Ticket;
import com.example.rentingsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Ticket findTicketById(Integer ticketId );

    List<Ticket> findAllByLessor(Lessor lessor);

    List<Ticket> findAllByRenter(Renter renter);


}

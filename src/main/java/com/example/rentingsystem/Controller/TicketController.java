package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Ticket;
import com.example.rentingsystem.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity getTickets (){
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTickets());
    }
    @PostMapping("/add")
    public ResponseEntity addTickets (Ticket ticket) {
        ticketService.addTicket(ticket);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added tickets"));
    }
    @DeleteMapping("/{ticketId}")
    public ResponseEntity removeTickets (Integer ticketId) {
        ticketService.removeTicket(ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted ticket"));
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity updateTickets (Ticket ticket,Integer ticketId) {
        ticketService.updateTicket(ticket,ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated ticket"));
    }

}

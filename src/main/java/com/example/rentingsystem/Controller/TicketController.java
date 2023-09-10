package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Ticket;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/get")
    public ResponseEntity getTickets(){
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTickets());
    }

    @GetMapping("get-by-id/{ticket_id}")
    public ResponseEntity getTicketById(@PathVariable Integer ticket_id){
        Ticket ticket = ticketService.getTicketById(ticket_id);

        return ResponseEntity.status(200).body(ticket);
    }

    @GetMapping("/resolve-ticket/{ticket_id}")
    public ResponseEntity resolveTicket(@PathVariable Integer ticket_id){
        ticketService.resolveTicket(ticket_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Ticket Resolved"));
    }

    @PostMapping("/add-l-ticket")
    public ResponseEntity addLessorTickets (@AuthenticationPrincipal User user, @RequestBody @Valid Ticket ticket) {
        ticketService.addLessorTickets(user.getLessor().getId(), ticket);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Ticket Added"));
    }

    @GetMapping("/get-l-tickets")
    public ResponseEntity getAllLessrTickets(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(ticketService.getLessorTickets(user.getLessor().getId()));

    }

    @DeleteMapping("/delete-l-ticket/{ticket_id}")
    public ResponseEntity removeLessorTicket(@AuthenticationPrincipal User user, @PathVariable Integer ticket_id){
        ticketService.deleteLessorTickets(user.getLessor().getId(), ticket_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Ticket Deleted"));
    }
    @PostMapping("/add-r-ticket")
    public ResponseEntity addRenterTickets (@AuthenticationPrincipal User user, @RequestBody @Valid Ticket ticket) {
        ticketService.addRenterTicket(user.getRenter().getId() ,ticket);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Ticket Added"));
    }

    @GetMapping("/get-r-tickets")
    public ResponseEntity getAllRenterTickets(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(ticketService.getRenterTickets(user.getRenter().getId()));
    }

    @DeleteMapping("/delete-r-ticket/{ticket_id}")
    public ResponseEntity removeRenterTicket(@AuthenticationPrincipal User user, @PathVariable Integer ticket_id){
        ticketService.deleteRenterTicket(user.getRenter().getId(), ticket_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("delete ticket"));
    }
    @PutMapping("update-r-ticket/{ticket_id}")
    public ResponseEntity updateRenterTickets (@AuthenticationPrincipal User user, @PathVariable Integer ticket_id, @RequestBody @Valid Ticket ticket) {
        ticketService.updateRenterTicket(user.getRenter().getId(),ticket_id,ticket);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Ticket Updated"));
    }

    @PutMapping("update-l-ticket/{ticket_id}")
    public ResponseEntity updateLessorTickets(@AuthenticationPrincipal User user, @PathVariable Integer ticket_id, @RequestBody @Valid Ticket ticket){
        ticketService.updateLessorTicket(user.getLessor().getId(), ticket_id, ticket);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Ticket Updated"));
    }





}

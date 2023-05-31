package com.example.train.controller;




import com.example.train.entity.Ticket;
import com.example.train.repository.TicketRepository;
import com.example.train.service.TicketBookingProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final TicketBookingProducer ticketBookingProducer;

    @Autowired
    public TicketController(TicketRepository ticketRepository, TicketBookingProducer ticketBookingProducer) {
        this.ticketRepository = ticketRepository;
        this.ticketBookingProducer = ticketBookingProducer;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElse(null);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketRepository.save(ticket);
        ticketBookingProducer.sendTicketBookingEvent(createdTicket); // Publish ticket booking event to Kafka
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket updatedTicket) {
        Ticket ticket = ticketRepository.findById(id)
                .orElse(null);
        if (ticket != null) {
            ticket.setPassengerName(updatedTicket.getPassengerName());
            ticket.setSource(updatedTicket.getSource());
            ticket.setDestination(updatedTicket.getDestination());
            Ticket savedTicket = ticketRepository.save(ticket);
            return new ResponseEntity<>(savedTicket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElse(null);
        if (ticket != null) {
            ticketRepository.delete(ticket);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

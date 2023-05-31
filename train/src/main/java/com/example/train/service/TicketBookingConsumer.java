package com.example.train.service;




import com.example.train.entity.Ticket;
import com.example.train.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingConsumer {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketBookingConsumer(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @KafkaListener(topics = "ticket-bookings", groupId = "ticket-group")
    public void consumeTicketBookingEvent(Ticket ticket) {
        ticketRepository.save(ticket);
        // Process the ticket booking event and update the database or perform any other necessary actions
    }
}


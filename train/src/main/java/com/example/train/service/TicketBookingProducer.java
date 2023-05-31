package com.example.train.service;



import com.example.train.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingProducer {

    private static final String TOPIC = "ticket-bookings";

    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    @Autowired
    public TicketBookingProducer(KafkaTemplate<String, Ticket> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTicketBookingEvent(Ticket ticket) {
        kafkaTemplate.send(TOPIC, ticket);
    }
}


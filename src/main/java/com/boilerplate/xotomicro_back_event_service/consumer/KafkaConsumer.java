package com.boilerplate.xotomicro_back_event_service.consumer;

import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.boilerplate.xotomicro_back_event_service.model.Event;
import com.boilerplate.xotomicro_back_event_service.service.EventService;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final EventService eventService;

    public KafkaConsumer(EventService eventService) {
        this.eventService = eventService;
    }

    @KafkaListener(topics = "${kafka.topics}")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Received event, topic={}, partition={}, offset={}", record.topic(), record.partition(), record.offset());
        logger.info("Value: {}", record.value());

        var event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setTopic(record.topic());
        event.setPartition(record.partition());
        event.setOffset(record.offset());
        event.setValue(record.value());
        event.setTimestamp(record.timestamp());
        eventService.save(event);
    }
}

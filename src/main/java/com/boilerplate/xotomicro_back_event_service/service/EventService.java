package com.boilerplate.xotomicro_back_event_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.boilerplate.xotomicro_back_event_service.model.Event;
import com.boilerplate.xotomicro_back_event_service.repository.EventRepository;

@Component
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAll(int page, int size) {
        return eventRepository.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
    }

    public void save(Event event) {
        eventRepository.save(event);
    }
}

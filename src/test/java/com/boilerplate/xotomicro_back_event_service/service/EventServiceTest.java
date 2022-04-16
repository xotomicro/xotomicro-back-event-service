package com.boilerplate.xotomicro_back_event_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.boilerplate.xotomicro_back_event_service.model.Event;
import com.boilerplate.xotomicro_back_event_service.repository.EventRepository;

@ExtendWith(SpringExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        List<Event> eventList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Event event = new Event();
            event.setId(String.valueOf(i));
            event.setOffset(i);
            event.setTimestamp(i);
            event.setTopic("Topic " + i);
            event.setPartition(i);
            event.setValue("Value " + i);
            eventList.add(event);
        }
        Page<Event> eventPage = new PageImpl<>(eventList);
        when(eventRepository.findAll(PageRequest.of(0, 5))).thenReturn(eventPage);
        when(eventRepository.findById("1")).thenReturn(java.util.Optional.ofNullable(eventList.get(0)));
    }

    @DisplayName("Get All Event")
    @Test
    void getAll() {
        assertEquals(5, eventService.getAll(0, 5).size());
        assertNotNull(eventService.getAll(0, 5));
    }

    @DisplayName("Save Event")
    @Test
    public void save() {
        eventService.save(eventRepository.findById("1").get());
    }
}

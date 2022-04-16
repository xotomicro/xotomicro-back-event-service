package com.boilerplate.xotomicro_back_event_service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boilerplate.xotomicro_back_event_service.model.Event;
import com.boilerplate.xotomicro_back_event_service.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ResponseBody
    @GetMapping
    public List<Event> getAll() {
        return eventService.getAll(0, 50);
    }
}

package com.boilerplate.xotomicro_back_event_service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.boilerplate.xotomicro_back_event_service.model.Event;

@Repository
public interface EventRepository extends ElasticsearchRepository<Event, String> {}

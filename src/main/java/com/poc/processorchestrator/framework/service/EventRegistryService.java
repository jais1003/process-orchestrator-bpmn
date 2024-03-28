package com.poc.processorchestrator.framework.service;

import com.poc.processorchestrator.framework.repository.EventRegistryRepository;
import com.poc.processorchestrator.framework.db.EventRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventRegistryService {

    @Autowired
    EventRegistryRepository eventRegistryRepository;

    public EventRegistry getEventConfig(String event_code){

        return eventRegistryRepository.findByEventcode(event_code);

    }
}

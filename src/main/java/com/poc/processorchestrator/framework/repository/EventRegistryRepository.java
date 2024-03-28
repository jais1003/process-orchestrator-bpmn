package com.poc.processorchestrator.framework.repository;

import com.poc.processorchestrator.framework.db.EventRegistry;
import org.springframework.data.repository.CrudRepository;

public interface EventRegistryRepository extends CrudRepository<EventRegistry, String> {

    EventRegistry findByEventcode(String event_code);
}

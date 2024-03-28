package com.poc.processorchestrator.framework.repository;

import com.poc.processorchestrator.framework.db.EventServiceMap;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventServiceMapRepository extends CrudRepository<EventServiceMap, String> {

    List<EventServiceMap> findByServicecode(String service_code);

    List<EventServiceMap> findByEventcode(String event_code);
}

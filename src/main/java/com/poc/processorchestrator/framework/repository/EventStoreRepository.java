package com.poc.processorchestrator.framework.repository;

import com.poc.processorchestrator.framework.db.EventStoreDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.lang.model.type.NullType;
import java.util.List;

public interface EventStoreRepository extends CrudRepository<EventStoreDB, String> {

    EventStoreDB findByCorrelationkeyAndEventcodeAndStatus(String correlationkey, String eventcode, String status);

    EventStoreDB findByCorrelationkeyAndServicenameAndStatus(String correlationkey, String servicename, String status);

    List<EventStoreDB> findBySrceventinstanceidAndStatus(String srcinstanceid, String status);

}

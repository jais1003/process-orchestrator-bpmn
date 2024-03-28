package com.poc.processorchestrator.services.repository;

import com.poc.processorchestrator.services.db.ProcessServiceMap;
import org.springframework.data.repository.CrudRepository;

public interface ProcessServiceMapRepository extends CrudRepository<ProcessServiceMap, String> {

    ProcessServiceMap findByProcessid(String process_id);
}

package com.poc.processorchestrator.framework.repository;

import com.poc.processorchestrator.common.model.ServiceConfig;
import com.poc.processorchestrator.framework.db.ServiceRegistry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRegistryRepository extends CrudRepository<ServiceRegistry, String> {

    List<ServiceRegistry> findByServicecode(String service_code);
    ServiceRegistry findByServicecodeAndVersion(String service_code, Integer version);
}

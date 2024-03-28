package com.poc.processorchestrator.framework.service;

import com.poc.processorchestrator.framework.repository.EventServiceMapRepository;
import com.poc.processorchestrator.framework.db.EventServiceMap;
import com.poc.processorchestrator.framework.db.ServiceRegistry;
import com.poc.processorchestrator.common.model.ServiceConfig;
import com.poc.processorchestrator.framework.repository.ServiceRegistryRepository;
import com.poc.processorchestrator.common.service.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ServiceRegistryService {

    @Autowired
    EventServiceMapRepository eventServiceMapRepository;

    @Autowired
    ServiceRegistryRepository serviceRegistryRepository;

    public List<ServiceConfig> getServiceConfigByEventCode(String event_code){

        List<ServiceConfig> serviceConfigList = new ArrayList<>();

        List<EventServiceMap> eventServiceMaplist = eventServiceMapRepository.findByEventcode(event_code);
        for(EventServiceMap eventServiceMap : eventServiceMaplist){

            //ServiceRegistry serviceRegistry = serviceRegistryRepository.findByServicecode(eventServiceMap.getServicecode());
            ServiceRegistry serviceRegistry = serviceRegistryRepository.findByServicecodeAndVersion(eventServiceMap.getServicecode(), eventServiceMap.getVersion());
            log.info("Service Registry : {}",CommonUtils.toJSON(serviceRegistry));

            if(serviceRegistry != null){

                serviceConfigList.add(ServiceConfig.builder()
                        .service_code(serviceRegistry.getServicecode())
                        .version(serviceRegistry.getVersion())
                        .service_name(serviceRegistry.getServicename())
                        .service_url(serviceRegistry.getServiceurl())
                        .type(serviceRegistry.getType())
                        .build());
            }
        }
        return serviceConfigList;
    }

    public ServiceConfig getServiceConfigByServiceCode(String service_code, Integer version){

        ServiceConfig serviceConfig = ServiceConfig.builder().build();

        ServiceRegistry serviceRegistry = serviceRegistryRepository.findByServicecodeAndVersion(service_code, version);
        if(serviceRegistry != null){

            serviceConfig.setService_url(serviceRegistry.getServiceurl());
            serviceConfig.setService_code(serviceRegistry.getServicecode());
            serviceConfig.setService_name(serviceRegistry.getServicename());
            serviceConfig.setVersion(serviceRegistry.getVersion());
            serviceConfig.setType(serviceRegistry.getType());
        }

        log.info("ServiceRegistryService.getServiceConfigByServiceCode() : {}",CommonUtils.toJSON(serviceConfig));
        return serviceConfig;
    }


}

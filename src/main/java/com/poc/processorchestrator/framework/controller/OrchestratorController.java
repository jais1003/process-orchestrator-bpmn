package com.poc.processorchestrator.framework.controller;

import com.poc.processorchestrator.common.model.EventData;
import com.poc.processorchestrator.common.model.ServiceConfig;
import com.poc.processorchestrator.common.service.CommonUtils;
import com.poc.processorchestrator.common.service.SharedServices;
import com.poc.processorchestrator.framework.db.EventRegistry;
import com.poc.processorchestrator.common.model.EventInstance;
import com.poc.processorchestrator.common.model.EventRequest;
import com.poc.processorchestrator.framework.db.EventStoreDB;
import com.poc.processorchestrator.framework.db.ServiceRegistry;
import com.poc.processorchestrator.framework.repository.EventStoreRepository;
import com.poc.processorchestrator.framework.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class OrchestratorController {

    @Autowired
    TriggerProcessService triggerProcessService;

    @Autowired
    EventRegistryService eventRegistryService;

    @Autowired
    ServiceRegistryService serviceRegistryService;

    @Autowired
    OrchestratorService orchestratorService;

    @Autowired
    EventStoreService eventStoreService;

    @Autowired
    EventStoreRepository eventStoreRepository;

    @Autowired
    SharedServices sharedServices;

    @PostMapping("/generate/event/prospect-completed")
    public EventInstance generateEventE1(@RequestBody String correlationKey) throws Exception {

        EventRequest eventRequest = EventRequest.builder()
                .correlation_key(correlationKey)
                .event_code("E1")
                .build();

        log.info("Correlation_key : {}",eventRequest.getCorrelation_key());

        EventInstance eventInstance = EventInstance.builder().build();

        EventRegistry eventRegistry = eventRegistryService.getEventConfig(eventRequest.getEvent_code());
        if(eventRegistry != null){


            if(eventRequest.getEvent_data() != null){

                Optional<EventInstance> eventInstanceOptional = CommonUtils.fromJSON(eventRequest.getEvent_data(), EventInstance.class);
                if(eventInstanceOptional.isPresent()){
                    eventInstance = createNewEventInstanceFromExisting(eventInstanceOptional.get(), eventRequest.getCorrelation_key());
                }


            }else if (eventRequest.getService_name() != null){

                    log.info("Correlation Key : {}, Service name : {}", eventRequest.getCorrelation_key(), eventRequest.getService_name());
                    EventStoreDB eventStoreDB = eventStoreRepository.findByCorrelationkeyAndServicenameAndStatus(
                            eventRequest.getCorrelation_key(),
                            eventRequest.getService_name(),
                            "Service Triggered");
                    eventInstance.setSrc_instance_id(eventStoreDB.getEventinstanceid());
                    eventInstance.setSource_service(ServiceConfig.builder()
                                    .service_name(eventRequest.getService_name())
                            .build());
                    log.info("OrchestratorController.generateEvent() : Set SrcEventinstanceID {}", eventStoreDB.getEventinstanceid());
            }

            eventInstance.setInstance_id(String.valueOf(UUID.randomUUID()));
            eventInstance.setEvent(eventRegistry);
            eventInstance.setCorrelation_key(eventRequest.getCorrelation_key());

            String publish_status = orchestratorService.publishEvent(eventInstance);
            if(publish_status.equalsIgnoreCase("Published")){
                eventInstance.setStatus(publish_status);
                sharedServices.persistEventInstance(eventInstance);

                log.info("OrchestratorController.generateEvent() : Event Generated : {}",CommonUtils.toJSON(eventInstance));
                return eventInstance;

            }else{
                throw new Exception("Event Publish Failed");
            }
        }else{
            throw new Exception("Event Code is not configured");
        }
    }

    @PostMapping("/generate/event/source-external-data")
    public EventInstance generateEventE15(@RequestBody String correlationKey) throws Exception {

        EventRequest eventRequest = EventRequest.builder()
                .correlation_key(correlationKey)
                .event_code("E15")
                .service_name("Entity Profile Service")
                .build();

        log.info("Correlation_key : {}",eventRequest.getCorrelation_key());

        EventInstance eventInstance = EventInstance.builder().build();

        EventRegistry eventRegistry = eventRegistryService.getEventConfig(eventRequest.getEvent_code());
        if(eventRegistry != null){


            if(eventRequest.getEvent_data() != null){

                Optional<EventInstance> eventInstanceOptional = CommonUtils.fromJSON(eventRequest.getEvent_data(), EventInstance.class);
                if(eventInstanceOptional.isPresent()){
                    eventInstance = createNewEventInstanceFromExisting(eventInstanceOptional.get(), eventRequest.getCorrelation_key());
                }


            }else if (eventRequest.getService_name() != null){

                log.info("Correlation Key : {}, Service name : {}", eventRequest.getCorrelation_key(), eventRequest.getService_name());
                EventStoreDB eventStoreDB = eventStoreRepository.findByCorrelationkeyAndServicenameAndStatus(
                        eventRequest.getCorrelation_key(),
                        eventRequest.getService_name(),
                        "Service Triggered");
                eventInstance.setSrc_instance_id(eventStoreDB.getEventinstanceid());
                eventInstance.setSource_service(ServiceConfig.builder()
                        .service_name(eventRequest.getService_name())
                        .build());
                log.info("OrchestratorController.generateEvent() : Set SrcEventinstanceID {}", eventStoreDB.getEventinstanceid());
            }

            eventInstance.setInstance_id(String.valueOf(UUID.randomUUID()));
            eventInstance.setEvent(eventRegistry);
            eventInstance.setCorrelation_key(eventRequest.getCorrelation_key());

            String publish_status = orchestratorService.publishEvent(eventInstance);
            if(publish_status.equalsIgnoreCase("Published")){
                eventInstance.setStatus(publish_status);
                sharedServices.persistEventInstance(eventInstance);

                log.info("OrchestratorController.generateEvent() : Event Generated : {}",CommonUtils.toJSON(eventInstance));
                return eventInstance;

            }else{
                throw new Exception("Event Publish Failed");
            }
        }else{
            throw new Exception("Event Code is not configured");
        }
    }

    @PostMapping("/generate/event/capture-hierarchy")
    public EventInstance generateEventE2(@RequestBody String correlationKey) throws Exception {

        EventRequest eventRequest = EventRequest.builder()
                .correlation_key(correlationKey)
                .event_code("E2")
                .service_name("Entity Profile Service")
                .build();

        log.info("Correlation_key : {}",eventRequest.getCorrelation_key());

        EventInstance eventInstance = EventInstance.builder().build();

        EventRegistry eventRegistry = eventRegistryService.getEventConfig(eventRequest.getEvent_code());
        if(eventRegistry != null){


            if(eventRequest.getEvent_data() != null){

                Optional<EventInstance> eventInstanceOptional = CommonUtils.fromJSON(eventRequest.getEvent_data(), EventInstance.class);
                if(eventInstanceOptional.isPresent()){
                    eventInstance = createNewEventInstanceFromExisting(eventInstanceOptional.get(), eventRequest.getCorrelation_key());
                }


            }else if (eventRequest.getService_name() != null){

                log.info("Correlation Key : {}, Service name : {}", eventRequest.getCorrelation_key(), eventRequest.getService_name());
                EventStoreDB eventStoreDB = eventStoreRepository.findByCorrelationkeyAndServicenameAndStatus(
                        eventRequest.getCorrelation_key(),
                        eventRequest.getService_name(),
                        "Service Triggered");
                eventInstance.setSrc_instance_id(eventStoreDB.getEventinstanceid());
                eventInstance.setSource_service(ServiceConfig.builder()
                        .service_name(eventRequest.getService_name())
                        .build());
                log.info("OrchestratorController.generateEvent() : Set SrcEventinstanceID {}", eventStoreDB.getEventinstanceid());
            }

            eventInstance.setInstance_id(String.valueOf(UUID.randomUUID()));
            eventInstance.setEvent(eventRegistry);
            eventInstance.setCorrelation_key(eventRequest.getCorrelation_key());

            String publish_status = orchestratorService.publishEvent(eventInstance);
            if(publish_status.equalsIgnoreCase("Published")){
                eventInstance.setStatus(publish_status);
                sharedServices.persistEventInstance(eventInstance);

                log.info("OrchestratorController.generateEvent() : Event Generated : {}",CommonUtils.toJSON(eventInstance));
                return eventInstance;

            }else{
                throw new Exception("Event Publish Failed");
            }
        }else{
            throw new Exception("Event Code is not configured");
        }
    }

    @PostMapping("/generate/event/unwrap-entity")
    public EventInstance generateEventE17(@RequestBody String correlationKey) throws Exception {

        EventRequest eventRequest = EventRequest.builder()
                .correlation_key(correlationKey)
                .event_code("E17")
                .service_name("KYC Profile Service")
                .build();

        log.info("Correlation_key : {}",eventRequest.getCorrelation_key());

        EventInstance eventInstance = EventInstance.builder().build();

        EventRegistry eventRegistry = eventRegistryService.getEventConfig(eventRequest.getEvent_code());
        if(eventRegistry != null){


            if(eventRequest.getEvent_data() != null){

                Optional<EventInstance> eventInstanceOptional = CommonUtils.fromJSON(eventRequest.getEvent_data(), EventInstance.class);
                if(eventInstanceOptional.isPresent()){
                    eventInstance = createNewEventInstanceFromExisting(eventInstanceOptional.get(), eventRequest.getCorrelation_key());
                }


            }else if (eventRequest.getService_name() != null){

                log.info("Correlation Key : {}, Service name : {}", eventRequest.getCorrelation_key(), eventRequest.getService_name());
                EventStoreDB eventStoreDB = eventStoreRepository.findByCorrelationkeyAndServicenameAndStatus(
                        eventRequest.getCorrelation_key(),
                        eventRequest.getService_name(),
                        "Service Triggered");
                eventInstance.setSrc_instance_id(eventStoreDB.getEventinstanceid());
                eventInstance.setSource_service(ServiceConfig.builder()
                        .service_name(eventRequest.getService_name())
                        .build());
                log.info("OrchestratorController.generateEvent() : Set SrcEventinstanceID {}", eventStoreDB.getEventinstanceid());
            }

            eventInstance.setInstance_id(String.valueOf(UUID.randomUUID()));
            eventInstance.setEvent(eventRegistry);
            eventInstance.setCorrelation_key(eventRequest.getCorrelation_key());

            String publish_status = orchestratorService.publishEvent(eventInstance);
            if(publish_status.equalsIgnoreCase("Published")){
                eventInstance.setStatus(publish_status);
                sharedServices.persistEventInstance(eventInstance);

                log.info("OrchestratorController.generateEvent() : Event Generated : {}",CommonUtils.toJSON(eventInstance));
                return eventInstance;

            }else{
                throw new Exception("Event Publish Failed");
            }
        }else{
            throw new Exception("Event Code is not configured");
        }
    }

    @PostMapping("/event/generate")
    public EventInstance generateEvent(@RequestBody EventRequest eventRequest) throws Exception {

        log.info("Correlation_key : {}, Event Request : {}",eventRequest.getCorrelation_key(), CommonUtils.toJSON(eventRequest));

        EventInstance eventInstance = EventInstance.builder().build();

        EventRegistry eventRegistry = eventRegistryService.getEventConfig(eventRequest.getEvent_code());
        if(eventRegistry != null){


            if(eventRequest.getEvent_data() != null){

                Optional<EventInstance> eventInstanceOptional = CommonUtils.fromJSON(eventRequest.getEvent_data(), EventInstance.class);
                if(eventInstanceOptional.isPresent()){
                    eventInstance = createNewEventInstanceFromExisting(eventInstanceOptional.get(), eventRequest.getCorrelation_key());
                }


            }else if (eventRequest.getService_name() != null){

                log.info("Correlation Key : {}, Service name : {}", eventRequest.getCorrelation_key(), eventRequest.getService_name());
                EventStoreDB eventStoreDB = eventStoreRepository.findByCorrelationkeyAndServicenameAndStatus(
                        eventRequest.getCorrelation_key(),
                        eventRequest.getService_name(),
                        "Service Triggered");
                eventInstance.setSrc_instance_id(eventStoreDB.getEventinstanceid());
                eventInstance.setSource_service(ServiceConfig.builder()
                        .service_name(eventRequest.getService_name())
                        .build());
                log.info("OrchestratorController.generateEvent() : Set SrcEventinstanceID {}", eventStoreDB.getEventinstanceid());
            }

            eventInstance.setInstance_id(String.valueOf(UUID.randomUUID()));
            eventInstance.setEvent(eventRegistry);
            eventInstance.setCorrelation_key(eventRequest.getCorrelation_key());

            String publish_status = orchestratorService.publishEvent(eventInstance);
            if(publish_status.equalsIgnoreCase("Published")){
                eventInstance.setStatus(publish_status);
                sharedServices.persistEventInstance(eventInstance);

                log.info("OrchestratorController.generateEvent() : Event Generated : {}",CommonUtils.toJSON(eventInstance));
                return eventInstance;

            }else{
                throw new Exception("Event Publish Failed");
            }
        }else{
            throw new Exception("Event Code is not configured");
        }
    }

    @CrossOrigin(origins = "http://localhost:63342/")
    @GetMapping("/instance/data/{correlationkey}/{eventcode}")
    public EventData fetchInstanceData(@PathVariable("correlationkey") String correlation_key, @PathVariable("eventcode") String event_code){

        return eventStoreService.fetchEventInstances(correlation_key, event_code);

    }

    @CrossOrigin(origins = "http://localhost:63342/")
    @GetMapping("/instance/data/{correlationkey}")
    public EventData fetchInstanceData(@PathVariable("correlationkey") String correlation_key){

        return eventStoreService.fetchEventInstances(correlation_key);

    }

    @GetMapping("/service/registry/{servicecode}/{version}")
    public ServiceConfig getServcieConfig(@PathVariable("servicecode") String service_code, @PathVariable("version") Integer version){

        return serviceRegistryService.getServiceConfigByServiceCode(service_code, version);
    }

    @GetMapping("/event/instance/{correlationkey}/{servicename}")
    public EventStoreDB getEventInstanceByService(@PathVariable("correlationkey") String correlationkey, @PathVariable("servicename") String servicename){

        log.info(correlationkey, servicename);
        return  eventStoreRepository.findByCorrelationkeyAndServicenameAndStatus(
                correlationkey, servicename, "Service Triggered");
    }

    private EventInstance createNewEventInstanceFromExisting(EventInstance srcEventInstance, String correlationKey){

        log.info("Setting old instance to new instance");

        EventInstance eventInstance =  EventInstance.builder()
                .source_service(srcEventInstance.getSource_service())
                .src_instance_id(srcEventInstance.getInstance_id())
                .entity_id(srcEventInstance.getEntity_id())
                .entity_name(srcEventInstance.getEntity_name())
                .entity_status(srcEventInstance.getEntity_status())
                .address(srcEventInstance.getAddress())
                .name_1(srcEventInstance.getName_1())
                .name_2(srcEventInstance.getName_2())
                .risk_rating(srcEventInstance.getRisk_rating())
                .shareholding_1(srcEventInstance.getShareholding_2())
                .entity_name_screening(srcEventInstance.getEntity_name_screening())
                .name_1_screening(srcEventInstance.getName_1_screening())
                .name_2_screening(srcEventInstance.getName_2_screening())
                .name_1_idtype(srcEventInstance.getName_1_idtype())
                .name_2_idtype(srcEventInstance.getName_2_idtype())
                .kyc_status(srcEventInstance.getKyc_status())
                .tax_status(srcEventInstance.getTax_status())
                .tax_country(srcEventInstance.getTax_country())
                .fatca_status(srcEventInstance.getFatca_status())
                .crs_status(srcEventInstance.getCrs_status())
                .name_2_id(srcEventInstance.getName_2_id())
                .name_1_id(srcEventInstance.getName_1_id())
                .avoxid(srcEventInstance.getAvoxid())
                .build();

        return eventInstance;
    }

}

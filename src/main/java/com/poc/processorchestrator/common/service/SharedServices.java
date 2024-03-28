package com.poc.processorchestrator.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.processorchestrator.common.model.EventInstance;
import com.poc.processorchestrator.framework.db.EventStoreDB;
import com.poc.processorchestrator.framework.repository.EventStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class SharedServices {

    private final RestTemplate restTemplate;
    private final EventStoreRepository eventStoreRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SharedServices(RestTemplate restTemplate, EventStoreRepository eventStoreRepository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.eventStoreRepository = eventStoreRepository;
        this.objectMapper = objectMapper;
    }

    public void persistEventInstance(EventInstance eventInstance) throws Exception {

        try{
            log.info("SharedServices.persistEventInstance() : EVentInstance : {}", CommonUtils.toJSON(eventInstance));
            log.warn("CORRELATION KEY : {} - EVENT INSTANCE ID : {} - EVENT NAME : {} - STATUS : {}",
                    eventInstance.getCorrelation_key(),
                    eventInstance.getInstance_id(),
                    eventInstance.getEvent().getEventname(),
                    eventInstance.getStatus());

            EventStoreDB eventStoreDB = new EventStoreDB();
            eventStoreDB.setEventinstanceid(eventInstance.getInstance_id());
            eventStoreDB.setCorrelationkey(eventInstance.getCorrelation_key());
            eventStoreDB.setEventcode(eventInstance.getEvent().getEventcode());
            eventStoreDB.setEventname(eventInstance.getEvent().getEventname());
            eventStoreDB.setStatus(eventInstance.getStatus());
            eventStoreDB.setSrceventinstanceid(eventInstance.getSrc_instance_id());

            if (eventInstance.getSource_service() != null) {
                log.info(" Service Name : {}",eventInstance.getSource_service().getService_name());

                eventStoreDB.setServicename(eventInstance.getSource_service().getService_name());
            }
            eventStoreRepository.save(eventStoreDB);
        }
        catch(Exception e){
            log.error("Exception while persisting the event : {}",e.getMessage());
            throw new Exception("Failed to persist Event data in DB", e);
        }

    }

    public String invokeServiceRESTEndpoint(String service_url, Object input_data) throws Exception {

        //log.info("Service URL : {}, Input Data : {}", service_url, CommonUtils.toJSON(input_data));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();

        //log.info("Request Body : {}", CommonUtils.toJSON(input_data));

        HttpEntity<Object> requestEntity = new HttpEntity<>(input_data, httpHeaders);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(service_url, HttpMethod.POST, requestEntity, Object.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){

            log.info("Response Body : {}", CommonUtils.toJSON(responseEntity.getBody()));
            return CommonUtils.toJSON(responseEntity.getBody());

        }else{
            throw new Exception("Failed to call Service Endpoint");
        }
    }
}

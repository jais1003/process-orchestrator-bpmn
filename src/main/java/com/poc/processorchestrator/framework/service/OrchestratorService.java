package com.poc.processorchestrator.framework.service;


import com.poc.processorchestrator.common.model.EventInstance;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrchestratorService {

    @Autowired
    ZeebeClient zeebeClient;

    public String publishEvent(EventInstance eventInstance){

        log.info("Correlation Key : {}", eventInstance.getCorrelation_key());

        zeebeClient.newPublishMessageCommand()
                .messageName(eventInstance.getEvent().getEventcode())
                .correlationKey(eventInstance.getCorrelation_key())
                .variables(eventInstance)
                .send()
                .join();
        return "Published";
    }
}

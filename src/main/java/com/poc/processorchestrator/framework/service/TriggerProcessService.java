package com.poc.processorchestrator.framework.service;

import com.poc.processorchestrator.framework.db.EventRegistry;
import com.poc.processorchestrator.common.model.EventInstance;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class TriggerProcessService {

    @Autowired
    ZeebeClient zeebeClient;

    @Autowired
    RuleEngineService ruleEngineService;

    @Autowired
    EventRegistryService eventRegistryService;

    public Boolean triggerEHProcess(String correlation_key){

        log.info("Correlation Key : {}", correlation_key);

        zeebeClient.newPublishMessageCommand()
                .messageName(ruleEngineService.getEHTriggerMessageID())
                .correlationKey(correlation_key)
                .send()
                .join();

        return Boolean.TRUE;
    }

    public Boolean triggerOnboardingProcess(String correlation_key){

        log.info("Correlation Key : {}", correlation_key);

        HashMap variableMap = new HashMap<>();
        variableMap.put("correlation_key",correlation_key);

        zeebeClient.newPublishMessageCommand()
                .messageName(ruleEngineService.getOnboardingMessageID())
                .correlationKey(correlation_key)
                .variables(variableMap)
                .send()
                .join();

        return Boolean.TRUE;
    }

    public Boolean triggerOnboardingProcessTemp(String correlation_key){

        log.info("Correlation Key : {}", correlation_key);

        EventRegistry event = eventRegistryService.getEventConfig("E4");

        EventInstance eventInstance = EventInstance.builder()
                .instance_id(String.valueOf(UUID.randomUUID()))
                .correlation_key(correlation_key)
                .status("CREATED")
                .event(event)
                .build();

        zeebeClient.newPublishMessageCommand()
                .messageName(ruleEngineService.getTempOnboardingMessageID())
                .correlationKey(correlation_key)
                .variables(eventInstance)
                .send()
                .join();

        return Boolean.TRUE;
    }
}

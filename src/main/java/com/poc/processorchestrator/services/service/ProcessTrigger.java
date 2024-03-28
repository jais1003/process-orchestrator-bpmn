package com.poc.processorchestrator.services.service;

import com.poc.processorchestrator.common.model.EventInstance;
import com.poc.processorchestrator.common.service.CommonUtils;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcessTrigger {

    @Autowired
    ZeebeClient zeebeClient;

    public Boolean triggerProcess(EventInstance eventInstance, String trigger_message){

        log.info("triggerProcess : EventInstance : {}, Trigger Message {}", CommonUtils.toJSON(eventInstance), trigger_message);

        zeebeClient.newPublishMessageCommand()
                .messageName(trigger_message)
                .correlationKey(eventInstance.getCorrelation_key())
                .variables(eventInstance)
                .send()
                .join();

        return Boolean.TRUE;
    }
}

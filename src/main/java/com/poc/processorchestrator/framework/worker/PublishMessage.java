package com.poc.processorchestrator.framework.worker;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class PublishMessage {
/*
    @Autowired
    ZeebeClient zeebeClient;

    //@ZeebeWorker(type = "publish-ep-completed", autoComplete = true)
    public Boolean publishEPCompletedMessage(final ActivatedJob activatedJob){

        log.info("Activated Job : {}", activatedJob.toJson());

        String correlation_key = (String)activatedJob.getVariablesAsMap().get("correlation_key");

        log.info("Correlation Key : {}", correlation_key);

        HashMap<String, Object> variablesMap = (HashMap)activatedJob.getVariablesAsMap();
        variablesMap.put("status", "EP Completed");

        log.info("inside publish message : {}, {}", activatedJob.getVariablesAsMap().keySet(), variablesMap.keySet());
        zeebeClient.newPublishMessageCommand()
                .messageName("ep-completed")
                .correlationKey(correlation_key)
                .variables(variablesMap)
                .send()
                .join();

        zeebeClient.newCompleteCommand(activatedJob.getKey()).send();

        return Boolean.TRUE;
    }

    //@ZeebeWorker(type = "publish-eh-completed", autoComplete = true)
    public Boolean publishEHCompletedMessage(final ActivatedJob activatedJob){

        log.info("Activated Job : {}", activatedJob.toJson());

        String correlation_key = (String)activatedJob.getVariablesAsMap().get("correlation_key");

        log.info("Correlation Key : {}", correlation_key);

        HashMap<String, Object> variablesMap = (HashMap)activatedJob.getVariablesAsMap();
        variablesMap.put("status", "EH Completed");

        log.info("inside publish message : {}, {}", activatedJob.getVariablesAsMap().keySet(), variablesMap.keySet());
        zeebeClient.newPublishMessageCommand()
                .messageName("eh-completed")
                .correlationKey(correlation_key)
                .variables(variablesMap)
                .send()
                .join();

        zeebeClient.newCompleteCommand(activatedJob.getKey()).send();

        return Boolean.TRUE;
    }

    //@ZeebeWorker(type = "publish-ev-completed", autoComplete = true)
    public Boolean publishEVCompletedMessage(final ActivatedJob activatedJob){

        log.info("Activated Job : {}", activatedJob.toJson());

        String correlation_key = (String)activatedJob.getVariablesAsMap().get("correlation_key");

        log.info("Correlation Key : {}", correlation_key);

        HashMap<String, Object> variablesMap = (HashMap)activatedJob.getVariablesAsMap();
        variablesMap.put("status", "EV Completed");

        log.info("inside publish message : {}, {}", activatedJob.getVariablesAsMap().keySet(), variablesMap.keySet());
        zeebeClient.newPublishMessageCommand()
                .messageName("ev-completed")
                .correlationKey(correlation_key)
                .variables(variablesMap)
                .send()
                .join();



        zeebeClient.newCompleteCommand(activatedJob.getKey())
                .send();

        return Boolean.TRUE;
    }*/
}

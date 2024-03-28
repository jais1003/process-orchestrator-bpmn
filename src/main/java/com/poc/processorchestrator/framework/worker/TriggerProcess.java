package com.poc.processorchestrator.framework.worker;

import com.poc.processorchestrator.framework.service.RuleEngineService;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TriggerProcess{
/*
    @Autowired
    ZeebeClient zeebeClient;

    @Autowired
    RuleEngineService ruleService;

    //@ZeebeWorker(type = "trigger-ep-svc", autoComplete = true)
    public Boolean triggerEPProcessLatest(final ActivatedJob activatedJob) {

        log.info("Trigger EP Process : {}", activatedJob.toJson());

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(ruleService.getEPProcessID())
                .latestVersion()
                .variables(activatedJob.getVariables())
                .send()
                .join()
        ;

        zeebeClient.newCompleteCommand(activatedJob.getKey())
                //.variables()
                .send();

        return Boolean.TRUE;
    }

    //@ZeebeWorker(type = "trigger-ev-svc", autoComplete = true)
    public Boolean triggerEVProcessLatest(final ActivatedJob activatedJob) {

        log.info("Trigger EV Process : {}", activatedJob.toJson());

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(ruleService.getEVProcessID())
                .latestVersion()
                .variables(activatedJob.getVariables())
                .send()
                .join()
        ;

        zeebeClient.newCompleteCommand(activatedJob.getKey()).send();

        return Boolean.TRUE;
    }

    //@ZeebeWorker(type = "trigger-eh-svc", autoComplete = true)
    public Boolean triggerEHProcessLatest(final ActivatedJob activatedJob) {

        log.info("Trigger EH Process : {}", activatedJob.toJson());

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(ruleService.getEHProcessID())
                .latestVersion()
                .variables(activatedJob.getVariables())
                .send()
                .join()
        ;

        zeebeClient.newCompleteCommand(activatedJob.getKey()).send();

        return Boolean.TRUE;
    }*/
}

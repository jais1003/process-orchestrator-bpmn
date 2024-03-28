package com.poc.processorchestrator.services.worker;

import com.poc.processorchestrator.common.model.EventInstance;
import com.poc.processorchestrator.common.model.EventRequest;
import com.poc.processorchestrator.common.service.CommonUtils;
import com.poc.processorchestrator.common.service.SharedServices;
import com.poc.processorchestrator.services.db.ProcessServiceMap;
import com.poc.processorchestrator.services.repository.ProcessServiceMapRepository;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.ZeebeCustomHeaders;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EventGenerator {

    @Autowired
    ProcessServiceMapRepository processServiceMapRepository;

    @Autowired
    SharedServices sharedServices;

    @Autowired
    ZeebeClient zeebeClient;


    @Value("${orchestrator.event.generator-endpoint}")
    private String generator_url;

    // Invoked by Type 2 services to generate new event publish to Orchestrator at the end of processing

    @ZeebeWorker(type = "publish-event", autoComplete = true)
    public Boolean publishEvent(final ActivatedJob activatedJob) throws Exception {

        log.info("publishEvent: ActivatedJob : {}",CommonUtils.toJSON(activatedJob));

        EventInstance eventInstance = CommonUtils.extractEventInstance(activatedJob);

        ProcessServiceMap processServiceMap = processServiceMapRepository.findByProcessid(activatedJob.getBpmnProcessId());
        if(processServiceMap != null){


            EventRequest eventRequest = EventRequest.builder()
                    .event_code(processServiceMap.getTerminaleventcode())
                    // Do not need to set service name. This will be set in event instance data
                    .event_data(CommonUtils.toJSON(eventInstance))
                    .correlation_key(eventInstance.getCorrelation_key())
                    .build();

            String result = sharedServices.invokeServiceRESTEndpoint(generator_url, eventRequest);
            if(!result.isEmpty()){

                log.info("publishEvent() : Generated new Event :  {}", result);

                Optional<EventInstance> eventInstanceOptional = CommonUtils.fromJSON(result, EventInstance.class);
                if (eventInstanceOptional.isPresent()){

                    log.info("Event Published");
                    //Marking the event as published
                    //sharedServices.persistEventInstance(eventInstanceOptional.get());
                }
            }
        }

        //Complete service task in service process
        zeebeClient.newCompleteCommand(activatedJob.getKey())
                .variables(eventInstance)
                .send();

        return Boolean.TRUE;
    }

    @ZeebeWorker(type = "call-vedaas", autoComplete = true)
    public Boolean invokeVedaasAPI(final ActivatedJob activatedJob) throws Exception {

        log.info("publishEvent: ActivatedJob : {}",CommonUtils.toJSON(activatedJob));

        //This is a dummy step. Nothing needs to be done here.

/*        EventInstance eventInstance = CommonUtils.extractEventInstance(activatedJob);
        eventInstance.setAvoxid("AVOX"+eventInstance.getEntity_id());

        ProcessServiceMap processServiceMap = processServiceMapRepository.findByProcessid(activatedJob.getBpmnProcessId());
        if(processServiceMap != null){


            EventRequest eventRequest = EventRequest.builder()
                    .event_code(processServiceMap.getTerminaleventcode())
                    .event_instance(eventInstance)
                    .correlation_key(eventInstance.getCorrelation_key())
                    .build();

            String result = sharedServices.invokeServiceRESTEndpoint(generator_url, eventRequest);
            if(!result.isEmpty()){
                log.info("invokeVedaasAPI() : Generated new Event :  {}", result);
            }
        }*/

        //Complete service task in service process
        zeebeClient.newCompleteCommand(activatedJob.getKey())
                //.variables(eventInstance)
                .send();

        return Boolean.TRUE;
    }

}

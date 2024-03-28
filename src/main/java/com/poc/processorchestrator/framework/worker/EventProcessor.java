package com.poc.processorchestrator.framework.worker;

import com.poc.processorchestrator.common.service.SharedServices;
import com.poc.processorchestrator.common.model.RoutingConfig;
import com.poc.processorchestrator.common.model.ServiceConfig;
import com.poc.processorchestrator.framework.service.ServiceRegistryService;
import com.poc.processorchestrator.common.service.CommonUtils;
import com.poc.processorchestrator.common.model.EventInstance;
import com.poc.processorchestrator.services.model.ServiceRequest;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventProcessor {

    @Autowired
    ZeebeClient zeebeClient;

    @Autowired
    ServiceRegistryService serviceRegistryService;

    @Autowired
    SharedServices sharedServices;

    //This is called by Orchestrator Service Task to invoke required services

    @ZeebeWorker(type = "event-processor", autoComplete = true)
    public Boolean processEvent(final ActivatedJob activatedJob) throws Exception {

        log.info("processEvent : Income event : {}", activatedJob.toJson());

        EventInstance srcEventInstance = CommonUtils.extractEventInstance(activatedJob);

        log.info("Correlation Key {}", srcEventInstance.getCorrelation_key());

        List<ServiceConfig> serviceConfigList = serviceRegistryService.getServiceConfigByEventCode(srcEventInstance.getEvent().getEventcode());

        //invoke REST endpoint for all services
        for (ServiceConfig serviceConfig : serviceConfigList){

            srcEventInstance.setSource_service(serviceConfig);
            String result = sharedServices.invokeServiceRESTEndpoint(
                    serviceConfig.getService_url(),
                    ServiceRequest.builder()
                            .eventInstance(srcEventInstance)
                            .build()
            );
            log.info("EventProcessor.processEvent(): API Response : {}",result);
            if(result.equalsIgnoreCase("true")){
                srcEventInstance.setStatus("Service Triggered");
                //Persist event as Published
                sharedServices.persistEventInstance(srcEventInstance);
            }
        }

        RoutingConfig routingConfig = CommonUtils.defineRoutingConfig(serviceConfigList);
        srcEventInstance.setRouting_config(routingConfig);

        log.info("Routing Config : {}", CommonUtils.toJSON(routingConfig));


        //Complete service task in Process orchestrator process
        zeebeClient.newCompleteCommand(activatedJob.getKey())
                .variables(srcEventInstance)
                .send();

        //srcEventInstance.setSource_service(serviceRegistryService.getServiceConfigByServiceCode("S0", 1));
        srcEventInstance.setStatus("Processed");
        //Persist event as Published
        sharedServices.persistEventInstance(srcEventInstance);

        return Boolean.TRUE;
    }
}

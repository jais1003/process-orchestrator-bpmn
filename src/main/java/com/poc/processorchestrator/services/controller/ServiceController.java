package com.poc.processorchestrator.services.controller;


import com.poc.processorchestrator.common.service.CommonUtils;
import com.poc.processorchestrator.services.model.ServiceRequest;
import com.poc.processorchestrator.services.service.ProcessTrigger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ServiceController {

    @Autowired
    ProcessTrigger processTrigger;

    @PostMapping("/service/entityprofile/invoke")
    public Boolean triggerEntityProfile(@RequestBody ServiceRequest serviceRequest){

        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-entityprofile");

    }
    @PostMapping("/service/entityprofile/invoke/v2")
    public Boolean triggerEntityProfileV2(@RequestBody ServiceRequest serviceRequest){

        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-entityprofilev2");

    }

    @PostMapping("/service/entityhierarchy/invoke")
    public Boolean triggerEntityHierarchy(@RequestBody ServiceRequest serviceRequest){

        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-entityhierarchy");

    }

    @PostMapping("/service/entityvalidation/invoke")
    public Boolean triggerEntityValidation(@RequestBody ServiceRequest serviceRequest){

        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-entityvalidation");

    }

    @PostMapping("/service/kycprofile/invoke")
    public Boolean triggerKYCProfile(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger KYC Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-kycprofile");

    }

    @PostMapping("/service/entityunwrapping/invoke")
    public Boolean triggerUnwrapping(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger Unwrapping Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-unwrapping");
    }

    @PostMapping("/service/idv/invoke")
    public Boolean triggerIDV(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger ID&V Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-idv");
    }

    @PostMapping("/service/screening/invoke")
    public Boolean triggerScreening(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger Screening Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-screening");
    }

    @PostMapping("/service/kycapprove/invoke")
    public Boolean triggerKYCApproval(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger KYC Approval Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-kycapprove");
    }

    @PostMapping("/service/pretax/invoke")
    public Boolean triggerPreTax(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger Pre Tax Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-pretax");
    }

    @PostMapping("/service/fatca/invoke")
    public Boolean triggerFATCA(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger FATCA Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-fatca");
    }

    @PostMapping("/service/crs/invoke")
    public Boolean triggerCRS(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger CRS Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-crs1");
    }

    @PostMapping("/service/externaldatasourcing/invoke")
    public Boolean triggerExternalDataSourcing(@RequestBody ServiceRequest serviceRequest){

        log.info("Trigger CRS Service {}", CommonUtils.toJSON(serviceRequest));
        return processTrigger.triggerProcess(serviceRequest.getEventInstance(), "trigger-vedaas");
    }




}

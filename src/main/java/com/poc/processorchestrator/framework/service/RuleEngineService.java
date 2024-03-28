package com.poc.processorchestrator.framework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RuleEngineService {

    public String getOnboardingMessageID(){
        return "trigger-onboarding";
    }
    public String getEPProcessID(){
        return "ep_process_v1";
    }

    public String getEHProcessID(){
        return "eh_process_v1";
    }

    public String getEVProcessID(){
        return "ev_process_v1";
    }

    public String getEHTriggerMessageID(){
        return "trigger-eh";
    }

    public String getEVTriggerMessageID(){
        return "trigger-ev";
    }

    public String getEPTriggerMessageID(){
        return "trigger-ep";
    }

    public String getTempOnboardingMessageID(){
        return "ev-completed";

    }

    public Boolean isKYCService(String service_code){
        if(service_code.equalsIgnoreCase("S4")){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    public Boolean isTaxService(String service_code){
        if(service_code.equalsIgnoreCase("S5")){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }
}

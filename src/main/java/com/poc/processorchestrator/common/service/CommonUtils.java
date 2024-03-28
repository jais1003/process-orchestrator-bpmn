package com.poc.processorchestrator.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.processorchestrator.common.model.EventInstance;
import com.poc.processorchestrator.common.model.RoutingConfig;
import com.poc.processorchestrator.common.model.ServiceConfig;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CommonUtils {

    public static <T> Optional<T> fromJSON(String json, Class<T> className){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.of(objectMapper.readValue(json, className));
        } catch (JsonMappingException e) {
            log.error("JSON : {} , ERROR : {}", json, e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            log.error("JSON : {} , ERROR : {}", json, e.getMessage());
            return Optional.empty();
        }
    }

    public static String toJSON(Object object){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("ERROR : {}", e.getMessage());
            return null;
        }
    }

    public static EventInstance extractEventInstance(ActivatedJob activatedJob) {

        EventInstance eventInstance = null;

        Optional<EventInstance> eventInstanceOptional = CommonUtils.fromJSON(activatedJob.getVariables(), EventInstance.class);
        if(eventInstanceOptional.isPresent()){
            eventInstance = eventInstanceOptional.get();
            log.info("Event Instance Data: {}",CommonUtils.toJSON(eventInstance));
        }
        return eventInstanceOptional.orElse(null);
    }

    public static RoutingConfig defineRoutingConfigOld(List<ServiceConfig> serviceConfigList){

        //log.info("defineRoutingConfig : {}", CommonUtils.toJSON(serviceConfigList));

        RoutingConfig routingConfig = RoutingConfig.builder()
                .isTaxApplicable(Boolean.FALSE)
                .isKYCApplicable(Boolean.FALSE)
                .isCRSApplicable(Boolean.FALSE)
                .isFATCAApplicable(Boolean.FALSE)
                .isIDVApplicable(Boolean.FALSE)
                .isScreeningApplicable(Boolean.FALSE)
                .build();

        for(ServiceConfig serviceConfig : serviceConfigList){
            if(serviceConfig.getType().equalsIgnoreCase("KYC")){
                routingConfig.setIsKYCApplicable(Boolean.TRUE);
            }
            if (serviceConfig.getType().equalsIgnoreCase("TAX")) {
                routingConfig.setIsTaxApplicable(Boolean.TRUE);
            }
            if (serviceConfig.getType().equalsIgnoreCase("FATCA")) {
                routingConfig.setIsFATCAApplicable(Boolean.TRUE);
            }
            if (serviceConfig.getType().equalsIgnoreCase("CRS")) {
                routingConfig.setIsCRSApplicable(Boolean.TRUE);
            }
            if (serviceConfig.getType().equalsIgnoreCase("SCREENING")) {
                routingConfig.setIsScreeningApplicable(Boolean.TRUE);
            }
            if (serviceConfig.getType().equalsIgnoreCase("IDV")) {
                routingConfig.setIsIDVApplicable(Boolean.TRUE);
            }
        }
        //log.info("defineRoutingConfig: After : {}",CommonUtils.toJSON(routingConfig));
        return routingConfig;
    }

    public static RoutingConfig defineRoutingConfig(List<ServiceConfig> serviceConfigList) {
        Map<String, Boolean> serviceApplicability = new HashMap<>();
        serviceConfigList.forEach(serviceConfig ->
                serviceApplicability.put(serviceConfig.getType().toUpperCase(), Boolean.TRUE)
        );

        return RoutingConfig.builder()
                .isTaxApplicable(serviceApplicability.getOrDefault("TAX", false))
                .isKYCApplicable(serviceApplicability.getOrDefault("KYC", false))
                .isCRSApplicable(serviceApplicability.getOrDefault("CRS", false))
                .isFATCAApplicable(serviceApplicability.getOrDefault("FATCA", false))
                .isIDVApplicable(serviceApplicability.getOrDefault("IDV", false))
                .isScreeningApplicable(serviceApplicability.getOrDefault("SCREENING", false))
                .build();
    }
}

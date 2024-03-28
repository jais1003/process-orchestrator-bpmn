package com.poc.processorchestrator.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poc.processorchestrator.framework.db.EventRegistry;
import lombok.*;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventInstance {

    private String instance_id;
    private String src_instance_id;
    private String correlation_key;
    private ServiceConfig source_service;
    private String status;
    private EventRegistry event;
    private RoutingConfig routing_config;
    private String entity_id;
    private String entity_name;
    private String entity_type;
    private String entity_status;
    private String address;
    private String name_1;
    private String name_2;
    private String risk_rating;
    private Integer shareholding_1;
    private Integer shareholding_2;
    private String entity_name_screening;
    private String name_1_screening;
    private String name_2_screening;
    private String name_1_idtype;
    private String name_2_idtype;
    private String name_1_id;
    private String name_2_id;
    private String kyc_status;
    private String tax_status;
    private String tax_country;
    private String fatca_status;
    private String crs_status;
    private String avoxid;

}

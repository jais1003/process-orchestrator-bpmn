package com.poc.processorchestrator.common.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceConfig {

    private String service_code;
    private Integer version;
    private String service_name;
    private String service_url;
    private String type;
}

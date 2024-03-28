package com.poc.processorchestrator.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventRequest {

    private String event_code;
    private String correlation_key;
    private String service_name;
    private String event_data;
}

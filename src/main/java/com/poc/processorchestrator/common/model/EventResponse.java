package com.poc.processorchestrator.common.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EventResponse {

    private String event_instance_id;
    private String status;
}

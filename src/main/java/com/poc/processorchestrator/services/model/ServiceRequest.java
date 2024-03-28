package com.poc.processorchestrator.services.model;

import com.poc.processorchestrator.common.model.EventInstance;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceRequest {
    private EventInstance eventInstance;
}

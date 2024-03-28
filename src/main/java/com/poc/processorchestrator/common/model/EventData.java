package com.poc.processorchestrator.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poc.processorchestrator.framework.db.EventStoreDB;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventData {

    private String event_instance_id;
    //private String src_event_instance_id;
    private String event_code;
    private String event_name;
    /*    private String correlation_key;
        private String status;
        private String service_name;*/
    private List<EventData> children;

    public EventData(String eventInstanceId, String eventCode, String eventName) {
        this.event_instance_id = eventInstanceId;
        this.event_code = eventCode;
        this.event_name = eventName;
    }
}

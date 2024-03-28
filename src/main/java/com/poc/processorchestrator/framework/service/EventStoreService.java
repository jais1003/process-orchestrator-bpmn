package com.poc.processorchestrator.framework.service;


import com.poc.processorchestrator.common.model.EventData;
import com.poc.processorchestrator.framework.db.EventStoreDB;
import com.poc.processorchestrator.framework.repository.EventStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventStoreService {

    @Autowired
    EventStoreRepository eventStoreRepository;

    public EventData fetchEventInstances(String correlationKey, String eventCode) {
        EventStoreDB eventStoreDB = eventStoreRepository.findByCorrelationkeyAndEventcodeAndStatus(
                correlationKey, eventCode, "Processed");

        EventData eventData = new EventData(
                eventStoreDB.getEventinstanceid(),
                eventStoreDB.getEventcode(),
                eventStoreDB.getEventname()
        );

        eventData.setChildren(getChildInstances(eventData));
        return eventData;
    }

    public EventData fetchEventInstances(String correlationKey) {
        EventStoreDB eventStoreDB = eventStoreRepository.findByCorrelationkeyAndServicenameAndStatus(
                correlationKey, null, "Published");

        EventData eventData = new EventData(
                eventStoreDB.getEventinstanceid(),
                eventStoreDB.getEventcode(),
                eventStoreDB.getEventname()
        );

        eventData.setChildren(getChildInstances(eventData));
        return eventData;
    }

    private List<EventData> getChildInstances(EventData eventData) {
        List<EventData> eventDataList = new ArrayList<>();
        List<EventStoreDB> eventStoreDBList = eventStoreRepository.findBySrceventinstanceidAndStatus(
                eventData.getEvent_instance_id(), "Published");

        eventDataList.addAll(eventStoreDBList.stream()
                .map(eventStoreDB -> new EventData(
                        eventStoreDB.getEventinstanceid(),
                        eventStoreDB.getEventcode(),
                        eventStoreDB.getEventname()
                ))
                .peek(eventData_in -> eventData_in.setChildren(getChildInstances(eventData_in)))
                .collect(Collectors.toList()));

        return eventDataList;
    }
}

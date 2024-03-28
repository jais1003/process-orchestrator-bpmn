package com.poc.processorchestrator.framework.db;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table
public class EventStoreDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime datetime;
    @Column
    private String eventinstanceid;
    @Column
    private String srceventinstanceid;
    @Column
    private String eventcode;
    @Column
    private String eventname;
    @Column
    private String correlationkey;
    @Column
    private String status;
    @Column
    private String servicename;

    @PrePersist
    private void prePersist() {
        // Set the creationDateTime to the current date and time before saving the entity
        this.datetime = LocalDateTime.now();
    }

}

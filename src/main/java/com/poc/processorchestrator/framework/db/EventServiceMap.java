package com.poc.processorchestrator.framework.db;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Table
@IdClass(EventServiceMap.class)
public class EventServiceMap implements Serializable {

    @Id
    @Column
    private String eventcode;
    @Id
    @Column
    private String servicecode;
    @Column
    private Integer version;


}

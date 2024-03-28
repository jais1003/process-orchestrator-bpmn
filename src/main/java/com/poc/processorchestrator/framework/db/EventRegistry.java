package com.poc.processorchestrator.framework.db;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table
public class EventRegistry{

    @Id
    @Column
    private String eventcode;
    @Column
    private String eventname;
}

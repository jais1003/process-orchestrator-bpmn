package com.poc.processorchestrator.services.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class ProcessServiceMap {

    @Id
    @Column
    private String processid;
    @Column
    private Integer processversion;
    @Column
    private String servicecode;
    @Column
    private Integer serviceversion;
    @Column
    private String terminaleventcode;
}

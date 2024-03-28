package com.poc.processorchestrator.framework.db;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table
@IdClass(ServiceRegistry.class)
public class ServiceRegistry implements Serializable {

    @Id
    @Column
    private String servicecode;
    @Id
    @Column
    private Integer version;
    @Column
    private String servicename;
    @Column
    private String serviceurl;
    @Column
    private String type;
}

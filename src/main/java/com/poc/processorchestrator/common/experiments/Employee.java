package com.poc.processorchestrator.common.experiments;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Employee {

    @Id
    private Long employeeid;
    private String name;

    @ManyToOne
    @JoinColumn(name = "departmentid")
    private Department department;
}

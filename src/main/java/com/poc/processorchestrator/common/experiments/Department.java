package com.poc.processorchestrator.common.experiments;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class Department {
    @Id
    private Long departmentid;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}

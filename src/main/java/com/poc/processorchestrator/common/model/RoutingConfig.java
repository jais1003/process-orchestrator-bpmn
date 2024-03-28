package com.poc.processorchestrator.common.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RoutingConfig{

    private Boolean isKYCApplicable = Boolean.FALSE;
    private Boolean isTaxApplicable = Boolean.FALSE;
    private Boolean isFATCAApplicable = Boolean.FALSE;
    private Boolean isCRSApplicable = Boolean.FALSE;
    private Boolean isScreeningApplicable = Boolean.FALSE;
    private Boolean isIDVApplicable = Boolean.FALSE;
}

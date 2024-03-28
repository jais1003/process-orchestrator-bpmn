package com.poc.processorchestrator;

import com.poc.processorchestrator.common.model.RoutingConfig;
import com.poc.processorchestrator.common.model.ServiceConfig;
import com.poc.processorchestrator.common.service.CommonUtils;
import com.poc.processorchestrator.framework.repository.ServiceRegistryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class ProcessOrchestratorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ServiceRegistryRepository serviceRegistryRepository;

	@Test
	public void generateServiceConfig(){

		ServiceConfig serviceConfig = ServiceConfig.builder()
				.service_code("123")
				.type("KYC")
				.version(1)
				.build();

		log.info("JSON : {}", CommonUtils.toJSON(serviceConfig));
	}

	@Test
	public void defineRoutingConfigTest(){

		List<ServiceConfig> serviceConfigList = new ArrayList<>();

		serviceConfigList.add(ServiceConfig.builder()
						.type("FATCA")
				.build());
		serviceConfigList.add(ServiceConfig.builder()
				.type("SCREENING").build());

		log.info("defineRoutingConfig : {}", CommonUtils.toJSON(serviceConfigList));

		RoutingConfig routingConfig = RoutingConfig.builder()
				.isTaxApplicable(Boolean.FALSE)
				.isKYCApplicable(Boolean.FALSE)
				.isCRSApplicable(Boolean.FALSE)
				.isFATCAApplicable(Boolean.FALSE)
				.isIDVApplicable(Boolean.FALSE)
				.isScreeningApplicable(Boolean.FALSE)
				.build();

		for(ServiceConfig serviceConfig : serviceConfigList){
			if(serviceConfig.getType().equalsIgnoreCase("KYC")){
				routingConfig.setIsKYCApplicable(Boolean.TRUE);
			}
			if (serviceConfig.getType().equalsIgnoreCase("TAX")) {
				routingConfig.setIsTaxApplicable(Boolean.TRUE);
			}
			if (serviceConfig.getType().equalsIgnoreCase("FATCA")) {
				routingConfig.setIsFATCAApplicable(Boolean.TRUE);
			}
			if (serviceConfig.getType().equalsIgnoreCase("CRS")) {
				routingConfig.setIsCRSApplicable(Boolean.TRUE);
			}
			if (serviceConfig.getType().equalsIgnoreCase("SCREENING")) {
				routingConfig.setIsScreeningApplicable(Boolean.TRUE);
			}
			if (serviceConfig.getType().equalsIgnoreCase("IDV")) {
				routingConfig.setIsIDVApplicable(Boolean.TRUE);
			}
		}
		log.info("defineRoutingConfig: After : {}",CommonUtils.toJSON(routingConfig));
	}



}

package com.poc.processorchestrator;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import io.camunda.zeebe.spring.client.config.ZeebeClientStarterAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableZeebeClient
@Import(ZeebeClientStarterAutoConfiguration.class)
@ZeebeDeployment(resources = "classpath:/bpmn/*.bpmn")
public class ProcessOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessOrchestratorApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}

package com.warad.employee_servcie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServcieApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServcieApplication.class, args);
	}

}

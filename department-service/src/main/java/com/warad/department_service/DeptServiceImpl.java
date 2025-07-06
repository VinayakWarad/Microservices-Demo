package com.warad.department_service;

import com.warad.department_service.client.WebClientConfig;
import com.warad.department_service.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    WebClientConfig webClientConfig;

    public List<Employee> getEmployeesByDepartmentId(Long deptId) {
        String employeeServiceUrl = "http://localhost:8083/employee/department/" + deptId;

        return webClientConfig.webClientBuilder()
                .build()
                .get()
                .uri(employeeServiceUrl)
                .retrieve()
                .bodyToFlux(Employee.class) // if expecting a list
                .collectList() // convert Flux<Employee> to Mono<List<Employee>>
                .block(); // blocking call, avoid in reactive flows
    }

    @Override
    public Employee saveEmp(Employee employee) {
        webClientConfig.webClientBuilder()
                .build()
                .post()
                .uri("http://localhost:8083/employee/")
                .bodyValue(employee)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
        return employee;
    }

}

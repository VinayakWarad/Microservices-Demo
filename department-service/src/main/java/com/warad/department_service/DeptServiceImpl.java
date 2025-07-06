package com.warad.department_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warad.department_service.client.WebClientConfig;
import com.warad.department_service.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    WebClientConfig webClientConfig;

    public List<Employee> getEmployeesByDepartmentId(Long deptId) {
        String employeeServiceUrl = "http://localhost:8083/employee/department/" + deptId;

        List<Employee> empList = webClientConfig.webClientBuilder()
                .build()
                .get()
                .uri(employeeServiceUrl)
                .retrieve()
                .bodyToFlux(Employee.class) // if expecting a list
                .collectList() // convert Flux<Employee> to Mono<List<Employee>>
                .block(); // blocking call, avoid in reactive flows
        return empList;
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

    public Employee getEmpJson(Long empId) {
        System.out.println("ENtered Service implementation");
        String jsonStr = webClientConfig.webClientBuilder()
                .build()
                .get()
                .uri("http://localhost:8083/employee/json/" + empId)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
/*Below can be used if you wanna get any specific field from your json response
        JsonObject jsonObj = JsonParser.parseString(jsonStr).getAsJsonObject();
System.out.println(jsonObj.get("name").toString());                  */
        System.out.println("Json Response in Str format" + jsonStr);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonStr, Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
